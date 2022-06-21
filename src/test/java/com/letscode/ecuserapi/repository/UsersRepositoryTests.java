package com.letscode.ecuserapi.repository;

import com.letscode.ecuserapi.domain.UserEntity;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UsersRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;

    @BeforeEach
    public void setup() {
        user = UserEntity.builder()
                .userName("RPS")
                .password("123")
                .name("Rubens")
                .email("rubens@email.com")
                .dateCreated(ZonedDateTime.now())
                .dateUpdated(ZonedDateTime.now())
                .build();

    }

    @DisplayName("JUnit test for save user operation")
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser() {

        UserEntity savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for find all users operation")
    @Test
    public void givenUsersList_whenFindAll_thenReturnUsersList() {

        UserEntity user2 = UserEntity.builder()
                .userName("Cindy")
                .password("123")
                .name("Cinderela")
                .email("cindy@email.com")
                .dateCreated(ZonedDateTime.now())
                .dateUpdated(ZonedDateTime.now())
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<UserEntity> users = userRepository.findAll();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for find user by id operation")
    @Test
    public void givenUserId_whenFindById_thenReturnUserObject() {

        userRepository.save(user);

        UserEntity userDb = userRepository.findById(user.getId()).get();

        assertThat(userDb).isNotNull();
        assertThat(userDb.getId()).isEqualTo(user.getId());
        assertThat(userDb.getName()).isEqualTo("Rubens");
    }

    @DisplayName("JUnit test for delete user by id operation")
    @Test
    public void givenUserId_whenDelete_thenReturnNull() {

        UserEntity savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());
        Optional<UserEntity> userOptional = userRepository.findById(savedUser.getId());

        assertThat(userOptional).isEmpty();
    }

}
