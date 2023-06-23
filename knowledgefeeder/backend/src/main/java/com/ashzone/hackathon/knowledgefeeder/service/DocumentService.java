package com.ashzone.hackathon.knowledgefeeder.service;

import com.ashzone.hackathon.knowledgefeeder.dto.DocumentDto;
import com.ashzone.hackathon.knowledgefeeder.exception.DocumentAlreadyExistsException;
import com.ashzone.hackathon.knowledgefeeder.model.Document;
import com.ashzone.hackathon.knowledgefeeder.repository.DocumentRespository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentService {

    Logger LOGGER = LoggerFactory.getLogger(DocumentService.class.getName());

    private final DocumentRespository documentRespository;

    public DocumentDto createDocument(DocumentDto documentDto) throws DocumentAlreadyExistsException {
        Document docModel = null;

        docModel = getDocumentByURL(documentDto.getUrl());

        if( null != docModel ){
            throw new DocumentAlreadyExistsException("Document already exists with url " + documentDto.getUrl());
        }

        docModel = new Document();
        docModel.setDescription(documentDto.getDescription());
        docModel.setInterestId(documentDto.getInterestId());
        docModel.setTitle(documentDto.getTitle());
        docModel.setUrl(documentDto.getUrl());
        docModel.setPublished(documentDto.isPublished());
        var createdDocument = documentRespository.save(docModel);
        documentDto.setId(createdDocument.getId());
        return documentDto;
    }


    public void updateDocument(Document document) {
        documentRespository.save(document);
    }

    public Document getDocumentByURL(final String documentURL) {
        return documentRespository.getDocumentByURL(documentURL);
    }


    public Document getDocumentByInterest(final String interestId) {
        return documentRespository.getDocumentByInterest(interestId);
    }
}
