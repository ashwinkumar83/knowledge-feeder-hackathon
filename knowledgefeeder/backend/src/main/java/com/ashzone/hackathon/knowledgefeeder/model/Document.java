package com.ashzone.hackathon.knowledgefeeder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document(value = "Documents")
@Builder
public class Document {
    private String id;
    private String title;
    private String description;
    private String interestId;
    private String url;
    private boolean published;
    private LocalDateTime publishedDate;
}