package com.ashzone.hackathon.knowledgefeeder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private String id;

    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Set<String> subscribedInterests;
    private Set<String> documentHistory;

}
