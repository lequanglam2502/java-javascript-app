package com.colin.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colin.app.entity.domain.Role;
import com.colin.app.entity.value.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Object findByName(ERole roleUser);
}
