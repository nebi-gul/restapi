package com.restapi.restapi.controller;

import com.restapi.restapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final List<User> users = new ArrayList<>();

    public UserController(){

        if(users.isEmpty()){
            User firstUser = new User("01", "Nebi", "GUL", "nebigul", 30,new Date());
            User secondUser = new User("02", "Ahmet", "SECER", "ahmetsecer", 28,new Date());
            User thirtUser = new User("03", "Taner", "YILDIRIM", "taneryildirim", 28,new Date());
            users.add(firstUser);
            users.add(secondUser);
            users.add(thirtUser);
        }

    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(users, OK); // static import
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User result = getUserById(id);
        return new ResponseEntity<>(result,OK);
    }

    private User getUserById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        newUser.setCreateDay(new Date());
        users.add(newUser);

        return new ResponseEntity<>(newUser, CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> getUser(@PathVariable String id,@RequestBody  User newUser) {
        User oldUser =getUserById(id);
        oldUser.setName(newUser.getName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setUserName(newUser.getUserName());
        oldUser.setAge(newUser.getAge());
        oldUser.setCreateDay(new Date());
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        User user =getUserById(id);
        users.remove(user);
        return new ResponseEntity<>(OK);
    }




}
