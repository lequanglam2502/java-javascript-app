package com.colin.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colin.app.entity.domain.AuditTrail;

@Repository
public interface AuditRepository extends JpaRepository<AuditTrail, Long> {
}
