package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public UsersResponse[] getAllUser(){
        UsersResponse[] usersResponses = new UsersResponse[2];
        usersResponses[0] = new UsersResponse(1, "user1");
        usersResponses[1] = new UsersResponse(1, "user2");
        return usersResponses;
    }
}
