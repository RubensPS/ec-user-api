package com.letscode.ecuserapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.ecuserapi.domain.UserEntity;
import com.letscode.ecuserapi.domain.UserRequest;
import com.letscode.ecuserapi.domain.UserResponse;
import com.letscode.ecuserapi.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest
@ExtendWith({MockitoExtension.class})
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity userEntity1 = new UserEntity(1, "RPS", "123", "Rubens", "rubens@email.com", ZonedDateTime.now(), ZonedDateTime .now());

    @DisplayName("unit test for addNewUser endpoint")
    @Test
    void givenUserRequestObject_whenAddNewUser_thenReturnResponseEntityUser() throws Exception {
        UserRequest request = new UserRequest("RPS", "123", "Rubens", "rubens@email.com");
        ResponseEntity<UserResponse> responseEntity = ResponseEntity.ok(new UserResponse(userEntity1));
        given(userService.addUser(ArgumentMatchers.any(UserRequest.class))).willAnswer(invocation -> responseEntity);

        ResultActions response = mockMvc.perform(post("/users/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(request.getUserName())))
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.email", is(request.getEmail())));

    }

    @DisplayName("unit test for getAllUsers endpoint")
    @Test
    void givenListOfUsers_whenGetAllUsers_then_returnResponseEntityUsersList() throws Exception {
        final UserEntity userEntity2 = new UserEntity(2, "CWR", "234", "Cinderela", "cindy@email.com", ZonedDateTime.now(), ZonedDateTime .now());
        List<UserResponse> usersList = new ArrayList<>();
        usersList.add(new UserResponse(userEntity1));
        usersList.add(new UserResponse(userEntity2));
        given(userService.getAllUsers()).willReturn(usersList);

        ResultActions response = mockMvc.perform(get("/users/all"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(usersList.size())));
    }

    @DisplayName("unit test for getUserById endpoint")
    @Test
    void givenUserId_whenGetUserById_thenReturnResponseEntityUser() throws Exception {
        given(userService.getUser(userEntity1.getId())).willReturn(Optional.of(userEntity1));

        ResultActions response = mockMvc.perform(get("/users/user/{userId}", userEntity1.getId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userEntity1.getId())))
                .andExpect(jsonPath("$.userName", is(userEntity1.getUserName())))
                .andExpect(jsonPath("$.name", is(userEntity1.getName())))
                .andExpect(jsonPath("$.email", is(userEntity1.getEmail())));
    }

    @DisplayName("unit test for getUserById endpoint when user does not exists")
    @Test
    void givenUserId_whenGetUserById_thenReturnResponseEntityNotFound() throws Exception {
        given(userService.getUser(userEntity1.getId())).willReturn(Optional.empty());

        ResultActions response = mockMvc.perform(get("/users/user/{userId}", userEntity1.getId()));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("unit test for deleteUserById endpoint")
    @Test
    void givenUserId_whenDeleteUserById_thenReturnResponseOk() throws Exception {
        String bodyMsg = "User DELETE successfully.";
        given(userService.deleteUser(userEntity1.getId())).willReturn(ResponseEntity.ok(bodyMsg));

        ResultActions response = mockMvc.perform(delete("/users//user/remove/{userId}", userEntity1.getId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(bodyMsg));
    }

    @DisplayName("unit test for deleteUserById endpoint when user does not exists")
    @Test
    void givenUserId_whenDeleteUserById_thenReturnResponseNotFound() throws Exception {
        given(userService.deleteUser(userEntity1.getId())).willReturn(ResponseEntity.notFound().build());

        ResultActions response = mockMvc.perform(delete("/users//user/remove/{userId}", userEntity1.getId()));

        response.andDo(print())
                .andExpect(status().isNotFound());

    }

}
