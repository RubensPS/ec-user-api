package com.letscode.ecusuarioapi.service;

import com.letscode.ecusuarioapi.domain.UserEntity;
import com.letscode.ecusuarioapi.domain.UserRequest;
import com.letscode.ecusuarioapi.domain.UserResponse;
import com.letscode.ecusuarioapi.gateway.CartGateway;
import com.letscode.ecusuarioapi.repository.UserRepository;
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
