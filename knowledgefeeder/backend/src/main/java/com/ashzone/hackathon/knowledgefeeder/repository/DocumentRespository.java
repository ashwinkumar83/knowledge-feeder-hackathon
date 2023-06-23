package com.ashzone.hackathon.knowledgefeeder.repository;

import com.ashzone.hackathon.knowledgefeeder.model.Document;
import org.springframework.data.repository.Repository;

public interface DocumentRespository extends Repository<Document, String> {
    public Document save(Document document);
    public Document getDocumentByURL(final String documentURL);
    public Document getDocumentByInterest(final String interestId);
}