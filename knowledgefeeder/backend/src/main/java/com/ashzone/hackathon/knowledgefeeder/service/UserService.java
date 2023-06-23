package com.ashzone.hackathon.knowledgefeeder.service;

import com.ashzone.hackathon.knowledgefeeder.dto.UserDto;
import com.ashzone.hackathon.knowledgefeeder.model.User;
import com.ashzone.hackathon.knowledgefeeder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

    public boolean registerUser(UserDto userDto){
        String emailId = userDto.getEmailAddress();
        boolean isNewUser = true;

        User user = getUserByEmailAddress(emailId);
        if(user!=null){
            isNewUser = false;
            LOGGER.info("User with provided email: " + emailId + " already exists. Please login.");
        }else {
            isNewUser = true;
            user = new User(emailId);
            user.setEmailAddress(userDto.getEmailAddress());
            user.setMailedDate(LocalDateTime.of(1900,01,01,00,00));
        }
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setSubscribedInterests(userDto.getSubscribedInterests());

        var registeredUser = userRepository.save(user);
        userDto.setId(user.getId());
        return isNewUser;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }
     User getUserByEmailAddress(String emailId){
         return userRepository.findByEmailAddress(emailId);
    }

    public List<User> getUsersByInterestAndMailedDate(final String interestId, final LocalDateTime mailedDate){
        return userRepository.findUsersByInterestAndMailedDate(interestId,mailedDate);
    }
}
