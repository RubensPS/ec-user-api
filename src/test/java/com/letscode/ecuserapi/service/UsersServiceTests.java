package com.letscode.ecuserapi.service;

import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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

    private UserEntity userEntity1 = new UserEntity(1, "RPS", "123", "Rubens", "rubens@email.com", ZonedDateTime.now(), ZonedDateTime .now());

    @DisplayName("JUnit test for add user operation")
    @Test
    void givenUserRequest_whenAddUser_thenReturnResponseEntityWithUserResponse() {
        UserRequest request = new UserRequest("RPS", "123", "Rubens", "rubens@email.com");
        BDDMockito.given(userRepository.findByName(request.getName())).willReturn(Optional.empty());
        BDDMockito.given(userRepository.save(any(UserEntity.class))).willReturn(userEntity1);
        userService = new UserService(userRepository);

        ResponseEntity response = userService.addUser(request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("JUnit test for add user operation when user already exists")
    @Test
    void givenUserRequest_whenAddUser_thenReturnResponseEntityConflict() {
        UserRequest request = new UserRequest("RPS", "123", "Rubens", "rubens@email.com");
        BDDMockito.given(userRepository.findByName(request.getName())).willReturn(Optional.of(userEntity1));
        userService = new UserService(userRepository);

        ResponseEntity response = userService.addUser(request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @DisplayName("JUnit test for delete user operation when user does not exists")
    @Test
    void givenUserId_whenDeleteUser_thenReturnResponseEntityNotFound() {
        ResponseEntity response = userService.deleteUser(userEntity1.getId());

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(userRepository, never()).deleteById(any(Integer.TYPE));
    }

    @Test
    void getUser() {
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
        final UserEntity userEntity2 = new UserEntity(2, "CWR", "234", "Cinderela", "cindy@email.com", ZonedDateTime.now(), ZonedDateTime .now());

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
        when(userRepository.findById(userEntity1.getId())).thenReturn(Optional.of(userEntity1));
        userService = new UserService(userRepository);

        ResponseEntity<String> response = userService.deleteUser(userEntity1.getId());

        verify(userRepository).deleteById(userEntity1.getId());
        assertFalse(response.getBody().isEmpty());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().equals("User DELETE successfully."));
    }

}
