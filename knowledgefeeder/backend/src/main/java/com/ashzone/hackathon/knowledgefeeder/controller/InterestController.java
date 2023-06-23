package com.ashzone.hackathon.knowledgefeeder.controller;

import com.ashzone.hackathon.knowledgefeeder.dto.InterestDto;
import com.ashzone.hackathon.knowledgefeeder.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interest")
public class InterestController {

    private final InterestService interestService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createInterest( @RequestBody InterestDto interestDto) {
        InterestDto createdInterestDto = interestService.createInterest(interestDto);
        return "Interest with name " + interestDto.getName()+" created";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllInterests")
    public List<InterestDto> getAllInterests() {
        return interestService.getAllInterests();
    }

}
