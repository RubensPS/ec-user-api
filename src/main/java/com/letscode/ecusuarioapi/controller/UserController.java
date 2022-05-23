package com.letscode.ecusuarioapi.controller;

import com.letscode.ecusuarioapi.domain.UserRequest;
import com.letscode.ecusuarioapi.domain.UserResponse;
import com.letscode.ecusuarioapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest request) {
        UserResponse response = userService.addUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        userService.getUserById(id);
        UserResponse response = userService.
        return ResponseEntity.ok(response);
    }

}
