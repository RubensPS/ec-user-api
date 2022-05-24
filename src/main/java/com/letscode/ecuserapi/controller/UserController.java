package com.letscode.ecuserapi.controller;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest request) {
        UserResponse response = userService.addUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        Optional<UserEntity> entity = userService.getUser(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserResponse response = new UserResponse(entity.get());
        return ResponseEntity.ok(response);
    }

}
