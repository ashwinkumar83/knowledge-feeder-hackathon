package com.ashzone.hackathon.knowledgefeeder.repository.impl;

import com.ashzone.hackathon.knowledgefeeder.model.Document;
import com.ashzone.hackathon.knowledgefeeder.repository.DocumentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class DocumentRespositoryImpl implements DocumentRespository {

    private final MongoOperations operations;

    @Autowired
    public DocumentRespositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Document save(final Document document) {
        return operations.save(document);
    }

    @Override
    public Document getDocumentByURL(final String documentURL) {
        Query query = query(where("url").is(documentURL));
        return operations.findOne(query, Document.class);
    }

    @Override
    public Document getDocumentByInterest(final String interestId) {
        Query query = query(where("interestId").is(interestId).and ("published").is(Boolean.FALSE));
        return operations.findOne(query, Document.class);
    }

}
