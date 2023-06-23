package com.ashzone.hackathon.knowledgefeeder.service;

import com.ashzone.hackathon.knowledgefeeder.dto.InterestDto;
import com.ashzone.hackathon.knowledgefeeder.model.Interest;
import com.ashzone.hackathon.knowledgefeeder.repository.InterestRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestService {

    private final InterestRespository interestRespository;

    public InterestDto createInterest(InterestDto interestDto) {
        Interest interest = new Interest();
        interest.setName(interestDto.getName());
        interest.setKeywords(interestDto.getKeywords());
        interestRespository.save(interest);

        return interestDto;
    }


    public List<InterestDto> getAllInterests() {
        List<InterestDto> interestDtos = new ArrayList<>();
        List<Interest> interests = interestRespository.findAllInterests();
        if(null!=interests){
            interests.forEach(interest -> {
                InterestDto interestDto = new InterestDto(interest.getName(),interest.getKeywords());
                interestDto.setId(interest.getId());
                interestDtos.add(interestDto);
            });
        }
        return interestDtos;
    }
}
