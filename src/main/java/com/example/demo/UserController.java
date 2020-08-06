package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public PagingResponse getAllUser(
             @RequestParam(defaultValue = "1") int page,
             @RequestParam(name = "item_per_page" ,defaultValue = "10") int itemPerPage){

        PagingResponse pagingResponse = new PagingResponse(page, itemPerPage);
        List<UsersResponse> usersResponseList = new ArrayList<>();
        Page<User> usersPagination = userRepository.findAll(PageRequest.of(page-1, itemPerPage));

//        usersResponseList.add(new UsersResponse(1, "user1"));
//        usersResponseList.add(new UsersResponse(2, "user2"));
//        usersResponseList.add(new UsersResponse(3, "user3"));
        Iterable<User> users = userRepository.findAll();
        for(User user: users){
            usersResponseList.add(new UsersResponse(user.getId(), user.getName()));
        }

        pagingResponse.setUsersResponse(usersResponseList);
        return pagingResponse;
    }



//    @GetMapping("/users")
//    public String getPage(@RequestParam(required = false,defaultValue = "1") String page, @RequestParam(name = "item_per_page",required = false,defaultValue = "15") String itemPerPage) {
//        return "Page: " + page + " , itemPerPage: " + itemPerPage;
//
//    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return new UsersResponse(user.get().getId(), user.get().getName());
    }

    //get value from obj
    @PostMapping("/users")
    public UsersResponse createNewUser(@RequestBody NewUserRequest request){
        // Validate input
        // Create new User Into database
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user = userRepository.save(user);
        return new UsersResponse(user.getId(), user.getName() + user.getAge());
    }

    //get value from form's submit
    @PostMapping("/users1")
    public String createNewUserWithFormData(NewUserRequest request){
        return request.getName() + request.getAge();
    }
}
