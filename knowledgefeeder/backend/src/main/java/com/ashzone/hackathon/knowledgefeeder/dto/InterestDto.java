package com.ashzone.hackathon.knowledgefeeder.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class InterestDto {

    private final String name;
    private final Set<String> keywords;
    private String Id;
}
