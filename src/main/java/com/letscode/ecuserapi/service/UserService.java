package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.gateway.CartGateway;
import com.letscode.ecuserapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final CartGateway cartGateway;

    public UserResponse addUser(UserRequest request) {
        UserEntity entity = request.toEntity();
        UserResponse response = new UserResponse(repository.save(entity));
        return response;
    }
}
