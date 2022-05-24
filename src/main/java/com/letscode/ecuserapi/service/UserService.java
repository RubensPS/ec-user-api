package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
