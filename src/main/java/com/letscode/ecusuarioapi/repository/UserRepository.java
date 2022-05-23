package com.letscode.ecusuarioapi.repository;

import com.letscode.ecusuarioapi.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
