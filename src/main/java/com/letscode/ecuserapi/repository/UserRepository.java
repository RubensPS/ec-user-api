package com.letscode.ecuserapi.repository;

import com.letscode.ecuserapi.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
