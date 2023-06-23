package com.ashzone.hackathon.knowledgefeeder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Users")
public class User {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Set<String> subscribedInterests = new HashSet<>();
    private Set<String> documentHistory = new HashSet<>();
    private LocalDateTime mailedDate;
    public User(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public void addToSubscribedInterest(String interestId) {
        subscribedInterests.add(interestId);
    }
 
    public void removeFromSubscribedInterest(String interestId) {
        subscribedInterests.remove(interestId);
    }

    public void addToDocHistory(String docId) {
        documentHistory.add(docId);
    }

}