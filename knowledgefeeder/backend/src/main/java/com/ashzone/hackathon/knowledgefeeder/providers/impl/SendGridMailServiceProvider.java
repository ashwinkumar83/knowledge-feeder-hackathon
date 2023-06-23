package com.ashzone.hackathon.knowledgefeeder.providers.impl;

import com.ashzone.hackathon.knowledgefeeder.jsonbean.Content;
import com.ashzone.hackathon.knowledgefeeder.jsonbean.EmailData;
import com.ashzone.hackathon.knowledgefeeder.jsonbean.Personalizations;
import com.ashzone.hackathon.knowledgefeeder.jsonbean.SendGridData;
import com.ashzone.hackathon.knowledgefeeder.providers.MailServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("sendGridMailServiceProvider")
public class SendGridMailServiceProvider implements MailServiceProvider {

    @Autowired
    private Environment env;
    @Override
    public HttpStatusCode sendEmail(final List<EmailData> toMailIds, final List<EmailData> bccMailIds,final String subject, final String body){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        env.getProperty("sendgrid.apikey");
        headers.setBearerAuth(env.getProperty("sendgrid.apikey"));

        HttpEntity<SendGridData> entity = new HttpEntity<SendGridData>(getMailData(toMailIds, bccMailIds, subject, body),headers);
        return restTemplate.exchange(env.getProperty("sendgrid.sendmail.url"), HttpMethod.POST, entity, String.class).getStatusCode();
    }

    private SendGridData getMailData(final List<EmailData> toMailIds, final List<EmailData> bccMailIds,final String subject, final String body){
        SendGridData mailData = new SendGridData();
        EmailData from = new EmailData(env.getProperty("sendgrid.mail.from.email"),env.getProperty("sendgrid.mail.from.name"));

        List<Personalizations> personalizationsList = new ArrayList<>();
        Personalizations personalizations = new Personalizations();

        if(!CollectionUtils.isEmpty(toMailIds)) {
            personalizations.setTo(toMailIds);
        }
        if(!CollectionUtils.isEmpty(bccMailIds)) {
            personalizations.setBcc(bccMailIds);
        }
        personalizationsList.add(personalizations);
        mailData.setPersonalizations(personalizationsList);

        mailData.setFrom(from);
        mailData.setSubject(subject);

        List<Content> contents = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        model.put("name", "ash");
        contents.add(new Content("text/html",body));
        mailData.setContent(contents);

        return mailData;
    }

}
