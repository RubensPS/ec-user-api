package com.letscode.ecuserapi.controller;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest request) {
        return userService.addUser(request);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        Optional<UserEntity> entity = userService.getUser(userId);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserResponse response = new UserResponse(entity.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/remove/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> entities = userService.getAllUsers();
        return ResponseEntity.ok(entities);
    }

}
