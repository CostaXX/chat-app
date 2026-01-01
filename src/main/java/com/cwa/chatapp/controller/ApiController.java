package com.cwa.chatapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwa.chatapp.entity.User;
import com.cwa.chatapp.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    
    private final UserService userService;

    @GetMapping("/online-users")
    public List<User> getOnlineUsers(@RequestParam String param) {
        return userService.getOnlineUsers();
    }
    

}
