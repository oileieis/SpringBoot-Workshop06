package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

//    @GetMapping("/users")
//    public List<UsersResponse> getAllUser(@RequestParam(required = false) int page, int itemPerPage){
//        List<UsersResponse> usersResponseList = new ArrayList<>();
//        usersResponseList.add(new UsersResponse(1, "user1"));
//        usersResponseList.add(new UsersResponse(2, "user2"));
//        usersResponseList.add(new UsersResponse(3, "user3"));
//        return usersResponseList;
//    }

    @GetMapping("/users")
    public String getPage(@RequestParam(required = false,defaultValue = "1") String page, @RequestParam(name = "item_per_page",required = false,defaultValue = "15") String itemPerPage) {
        return "Page: " + page + " , itemPerPage: " + itemPerPage;

    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public UsersResponse getUserById(@PathVariable int id){
        return new UsersResponse(id, "User " + id);
    }
}
