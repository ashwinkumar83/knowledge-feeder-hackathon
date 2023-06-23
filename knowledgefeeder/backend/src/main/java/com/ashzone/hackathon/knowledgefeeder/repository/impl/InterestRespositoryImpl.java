package com.ashzone.hackathon.knowledgefeeder.repository.impl;

import com.ashzone.hackathon.knowledgefeeder.model.Interest;
import com.ashzone.hackathon.knowledgefeeder.repository.InterestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

public class InterestRespositoryImpl implements InterestRespository {

    private final MongoOperations operations;

    @Autowired
    public InterestRespositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Interest save(final Interest interest) {
        return operations.save(interest);
    }

    @Override
    public List<Interest> findAllInterests() {
        return operations.findAll(Interest.class);
    }

}
