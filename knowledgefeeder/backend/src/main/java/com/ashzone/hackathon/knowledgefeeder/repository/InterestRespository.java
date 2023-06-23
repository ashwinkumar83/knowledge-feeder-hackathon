package com.ashzone.hackathon.knowledgefeeder.repository;

import com.ashzone.hackathon.knowledgefeeder.model.Interest;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface InterestRespository extends Repository<Interest, String> {
    public Interest save(Interest interest);
    public List<Interest> findAllInterests();
}