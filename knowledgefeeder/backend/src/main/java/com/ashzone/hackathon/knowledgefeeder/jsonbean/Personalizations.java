/* Copyright 2023 freecodeformat.com */
package com.ashzone.hackathon.knowledgefeeder.jsonbean;
import lombok.Data;

import java.util.List;

@Data
public class Personalizations {
    private List<EmailData> to;
    private List<EmailData> cc;
    private List<EmailData> bcc;
}