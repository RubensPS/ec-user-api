package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @CacheEvict(cacheNames = "users", allEntries = true)
    public ResponseEntity<UserResponse> addUser(UserRequest request) {
        Optional<UserEntity> entity = repository.findByName(request.getName());
        if (entity.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        UserEntity user = request.toEntity();
        UserResponse response = new UserResponse(repository.save(user));
        return ResponseEntity.ok(response);
    }

    public Optional<UserEntity> getUser(Integer id) {
        Optional<UserEntity> response = repository.findById(id);
        return response;
    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    public ResponseEntity<String> deleteUser(Integer userId) {
        Optional<UserEntity> entity = repository.findById(userId);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(entity.get().getId());
        return ResponseEntity.ok("User DELETE successfully.");
    }

    @Cacheable(cacheNames = "users")
    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(entity -> new UserResponse(entity))
                .collect(Collectors.toList());
    }

}
