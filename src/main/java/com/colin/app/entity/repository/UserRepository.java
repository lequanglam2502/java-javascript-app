package com.colin.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colin.app.entity.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Object findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
