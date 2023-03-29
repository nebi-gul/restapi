package com.restapi.restapi.controller;

import com.restapi.restapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final List<User> users;

    public UserController(){
        User firstUser = new User("01", "Nebi", "GUL", "nebigul", 30);
        User secondUser = new User("02", "Ahmet", "SECER", "ahmetsecer", 28);
        User thirtUser = new User("03", "Taner", "YILDIRIM", "taneryildirim", 28);
         users = Arrays.asList(firstUser, secondUser,thirtUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(users, OK); // static import
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User result = users.stream()
                .filter( user -> user.getId().equals(id))
                .findFirst().orElseThrow(()->new RuntimeException("User not found"));
        return new ResponseEntity<>(result,OK);
    }



}