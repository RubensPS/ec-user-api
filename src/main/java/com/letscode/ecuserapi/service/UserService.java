package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse addUser(UserRequest request) {
        UserEntity entity = request.toEntity();
        UserResponse response = new UserResponse(repository.save(entity));
        return response;
    }

    public Optional<UserEntity> getUser(Integer id) {
        Optional<UserEntity> response = repository.findById(id);
        return response;
    }

    public ResponseEntity<String> deleteUser(Integer userId) {
        Optional<UserEntity> entity = repository.findById(userId);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(entity.get().getId());
        return ResponseEntity.ok("User DELETE successfully.");
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(entity -> new UserResponse(entity))
                .collect(Collectors.toList());
    }

}
