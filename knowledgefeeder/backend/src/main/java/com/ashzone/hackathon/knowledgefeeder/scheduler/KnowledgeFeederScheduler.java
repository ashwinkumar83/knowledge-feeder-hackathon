package com.ashzone.hackathon.knowledgefeeder.scheduler;

import com.ashzone.hackathon.knowledgefeeder.dto.DocumentDto;
import com.ashzone.hackathon.knowledgefeeder.dto.InterestDto;
import com.ashzone.hackathon.knowledgefeeder.exception.DocumentAlreadyExistsException;
import com.ashzone.hackathon.knowledgefeeder.model.Document;
import com.ashzone.hackathon.knowledgefeeder.model.User;
import com.ashzone.hackathon.knowledgefeeder.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


@Component
public class KnowledgeFeederScheduler {

    private Logger LOGGER = LoggerFactory.getLogger(KnowledgeFeederScheduler.class.getName());
    @Autowired
    private UserService userService;

    @Autowired
    private InterestService interestService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DocumentScraperService documentScraperService;

    @Scheduled(cron = "0 0 9 * * SAT")
    public void sendEmails(){
        LocalDateTime currDate = LocalDateTime.now();
        List<InterestDto> configuredInterests = interestService.getAllInterests();
        configuredInterests.forEach(interest -> {
            Document doc = documentService.getDocumentByInterest(interest.getId());
            if(null!=doc && null!=doc.getUrl()) {
                List<User> subscribedUsers = userService.getUsersByInterestAndMailedDate(interest.getId(), currDate );
                subscribedUsers.parallelStream().forEach(user -> {
                    emailService.sendEmail(user,doc.getUrl());
                    LOGGER.info( "Email send to user " + user.getEmailAddress() + " with document " + doc.getUrl());

                    user.addToDocHistory(doc.getId());
                    user.setMailedDate(LocalDateTime.now());
                    userService.updateUser(user);
                });

                doc.setPublished(Boolean.TRUE);
                doc.setPublishedDate(LocalDateTime.now());
                documentService.updateDocument(doc);
            }
        });

    }

    // We can enable the remote chrome driver if the scraping machine is in a remote server
    @Async
    @Scheduled(cron = "0 0 9 * * FRI")
    public void addDocuments() {
        List<InterestDto> interestDtos = interestService.getAllInterests();
        interestDtos.stream().forEach(d -> {
            Set<String> keywords = d.getKeywords();

            if(keywords.isEmpty()) return;

            Random random = new Random();
            int choice = random.nextInt(keywords.size());
            String keyword = (String)keywords.toArray()[choice];
            Map<String,String> docMap = documentScraperService.addDocuments(keyword);
            if(!docMap.isEmpty())addDocumentForInterest(d.getId(), docMap);
        });

    }

    private void addDocumentForInterest(final String interestId, final Map<String,String> docMap ) {
        if(docMap.isEmpty()) return;
        docMap.forEach((k,v)->{
            System.out.println("Key : " + k + " Value : " + v);
            DocumentDto documentDto = new DocumentDto(k,v);
            documentDto.setInterestId(interestId);
            documentDto.setPublished(false);
            documentDto.setDescription(v);
            try {
                documentService.createDocument(documentDto);
            } catch (DocumentAlreadyExistsException e) {
                LOGGER.error(e.getMessage());
            }
        });
    }
}
