package com.ashzone.hackathon.knowledgefeeder.service;

import com.ashzone.hackathon.knowledgefeeder.jsonbean.EmailData;
import com.ashzone.hackathon.knowledgefeeder.model.User;
import com.ashzone.hackathon.knowledgefeeder.providers.MailServiceProvider;
import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class EmailService {
private Logger LOGGER = LoggerFactory.getLogger(EmailService.class.getName());
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Configuration config;

    @Autowired
    @Qualifier("sendGridMailServiceProvider")
    MailServiceProvider mailServiceProvider;

    public HttpStatusCode sendEmail(User user, String docUrl){
        List<EmailData> toMails = new ArrayList<>();
        toMails.add(new EmailData(user.getEmailAddress(),user.getFirstName()));

        List<EmailData> bccMails = new ArrayList<>();
        //bccMails.add(new EmailData(user.getEmailAddress(),user.getFirstName()));

        String subject = "Feeding knowledge as per your interest. Enrich yourself.";

        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirstName());
        model.put("docUrl", docUrl);

        String body = geContentFromTemplate(model);

        return mailServiceProvider.sendEmail(toMails, bccMails, subject, body);
    }

    public String geContentFromTemplate(Map<String, Object> model)     {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(config.getTemplate("email-weeklyTemplate.flth"), model));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return content.toString();
    }
}
