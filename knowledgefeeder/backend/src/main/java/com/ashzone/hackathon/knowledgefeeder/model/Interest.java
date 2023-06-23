package com.ashzone.hackathon.knowledgefeeder.model;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document(value = "Interests")
@Builder
public class Interest {
    @Id
    private String id;
    private String name;
    private Set<String> keywords = new HashSet<>();

    public Set<String> getKeywords(){
        return this.keywords;
    }

    public Set<String> addKeywords(final String keyword){
        this.keywords.add(keyword);
        return this.keywords;
    }

    public Set<String> removeKeywords(final String keyword){
        this.keywords.remove(keyword);
        return this.keywords;
    }
}