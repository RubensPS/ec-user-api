package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser() {
        final UserEntity userEntity1 = new UserEntity("RPS", "123", "Rubens", "rubens@email.com", ZonedDateTime.now(), ZonedDateTime .now());

        when(userRepository.findById(userEntity1.getId())).thenReturn(Optional.of(userEntity1));
        userService = new UserService(userRepository);

        Optional<UserEntity> user = userService.getUser(userEntity1.getId());
        assertFalse(user.isEmpty());
        assertTrue(user.isPresent());
        assertEquals(userEntity1.getUserName(), "RPS");
        assertEquals(userEntity1.getPassword(), "123");
        assertEquals(userEntity1.getName(), "Rubens");
        assertEquals(userEntity1.getEmail(), "rubens@email.com");
    }

    @Test
    void getAllUsers() {
        final UserEntity userEntity1 = new UserEntity("RPS", "123", "Rubens", "rubens@email.com", ZonedDateTime.now(), ZonedDateTime .now());
        final UserEntity userEntity2 = new UserEntity("CWR", "234", "Cinderela", "cindy@email.com", ZonedDateTime.now(), ZonedDateTime .now());

        when(userRepository.findAll()).thenReturn(List.of(userEntity1, userEntity2));
        userService = new UserService(userRepository);

        List<UserResponse> users = userService.getAllUsers();
        assertEquals(users.size(), 2);
        assertEquals(users.get(1).getName(), "Cinderela");
        assertEquals(users.get(0).getEmail(), "rubens@email.com");
        assertFalse(users.isEmpty());
    }

    @Test
    void deleteUser() {
        final UserEntity userEntity1 = new UserEntity("RPS", "123", "Rubens", "rubens@email.com", ZonedDateTime.now(), ZonedDateTime .now());

        when(userRepository.findById(userEntity1.getId())).thenReturn(Optional.of(userEntity1));
        userService = new UserService(userRepository);

        ResponseEntity<String> response = userService.deleteUser(userEntity1.getId());
        verify(userRepository).deleteById(userEntity1.getId());
        assertFalse(response.getBody().isEmpty());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().equals("User DELETE successfully."));


    }


}
