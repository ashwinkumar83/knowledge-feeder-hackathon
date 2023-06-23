/* Copyright 2023 freecodeformat.com */
package com.ashzone.hackathon.knowledgefeeder.jsonbean;

import lombok.Data;

@Data
public class Content {
    private String type;
    private String value;
    public Content(String type, String value){
        this.type = type;
        this.value = value;
    }
}