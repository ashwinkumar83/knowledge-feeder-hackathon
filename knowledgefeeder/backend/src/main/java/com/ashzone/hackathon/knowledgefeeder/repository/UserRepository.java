package com.ashzone.hackathon.knowledgefeeder.repository;

import com.ashzone.hackathon.knowledgefeeder.model.User;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends Repository<User, String> {
    public User findByEmailAddress(final String emailAddress);
    public User save(final User user);

    public List<User> findUsersByInterestAndMailedDate(final String emailAddress, final LocalDateTime mailedDate );
}
