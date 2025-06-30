package org.mck.userservice.controller;


import lombok.RequiredArgsConstructor;
import org.mck.userservice.domain.User;
import org.mck.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User newUser){
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getUser(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
