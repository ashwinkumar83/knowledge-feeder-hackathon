package com.ashzone.hackathon.knowledgefeeder.providers;

import com.ashzone.hackathon.knowledgefeeder.jsonbean.EmailData;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface MailServiceProvider {
    public HttpStatusCode sendEmail(final List<EmailData> toMailIds, final List<EmailData> bccMailIds, final String subject, final String body);
}
