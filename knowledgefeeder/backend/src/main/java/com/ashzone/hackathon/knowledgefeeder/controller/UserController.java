package com.ashzone.hackathon.knowledgefeeder.controller;


import com.ashzone.hackathon.knowledgefeeder.dto.UserDto;
import com.ashzone.hackathon.knowledgefeeder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
            boolean isNewUser =  userService.registerUser(userDto);
            if(isNewUser) {
                return "User created with email address : " + userDto.getEmailAddress() + ". You are subscribed to our weekly feeds  based on your interests";
            }else{
                return "User details of email address : " + userDto.getEmailAddress() + "are updated. " +
                        "You are subscribed to our weekly feeds based on your interests";
            }
    }
}
