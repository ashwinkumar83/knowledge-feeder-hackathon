package com.ashzone.hackathon.knowledgefeeder.repository.impl;

import com.ashzone.hackathon.knowledgefeeder.model.User;
import com.ashzone.hackathon.knowledgefeeder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoOperations operations;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public User findByEmailAddress(String emailAddress){
        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, User.class);
    }

    @Override
    public User save(User user){
        operations.save(user);
        return user;
    }

    @Override
    public List<User> findUsersByInterestAndMailedDate(String interestId, LocalDateTime mailedDate ) {
        Query query = query(where("subscribedInterests").in(interestId));
               // .andOperator(new Criteria("mailedDate").isNull().orOperator(new Criteria("mailedDate").lt(mailedDate))));
        System.out.println("fired query " + query.toString());
        return operations.find(query, User.class);
    }
}
