/* Copyright 2023 freecodeformat.com */
package com.ashzone.hackathon.knowledgefeeder.jsonbean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SendGridData {
    private List<Personalizations> personalizations;
    private EmailData from;
    @JsonProperty("reply_to")
    private EmailData replyTo;
    private String subject;
    private List<Content> content;
    private List<Attachments> attachments;
    private List<String> categories;
    @JsonProperty("send_at")
    private int sendAt;
    @JsonProperty("batch_id")
    private String batchId;
}