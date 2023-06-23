package com.ashzone.hackathon.knowledgefeeder.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DocumentDto {
    private String id;
    private final String title;
    private String description;
    private String interestId;
    private final String url;
    private boolean published;
    private LocalDateTime publishedDate;

    public DocumentDto(final String title, final String url){
        this.title = title;
        this.url = url;
    }

}
