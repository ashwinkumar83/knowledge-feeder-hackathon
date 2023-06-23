/* Copyright 2023 freecodeformat.com */
package com.ashzone.hackathon.knowledgefeeder.jsonbean;

import lombok.Data;

@Data
public class EmailData {

    private String email;
    private String name;
    public EmailData(String email, String name){
        this.email = email;
        this.name = name;
    }

}