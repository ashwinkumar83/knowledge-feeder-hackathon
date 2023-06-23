package com.ashzone.hackathon.knowledgefeeder.controller;

import com.ashzone.hackathon.knowledgefeeder.dto.DocumentDto;
import com.ashzone.hackathon.knowledgefeeder.exception.DocumentAlreadyExistsException;
import com.ashzone.hackathon.knowledgefeeder.scheduler.KnowledgeFeederScheduler;
import com.ashzone.hackathon.knowledgefeeder.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final  DocumentService documentService;
    private final KnowledgeFeederScheduler scheduler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createDocument( @RequestBody DocumentDto documentDto) {
        try {
            if(null==documentDto || null==documentDto.getUrl()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Document with url should be provided");
            }
            documentDto.setPublished(Boolean.FALSE);
            DocumentDto docDto = documentService.createDocument(documentDto);
        } catch (DocumentAlreadyExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return "Document with title " + documentDto.getTitle() +" created";
    }

    /* This is to trigger the document scraper via API */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/startDocumentScraper")
    public String startDocumentScraper() {
        scheduler.addDocuments();
        return "Document collection process started";
    }

}
