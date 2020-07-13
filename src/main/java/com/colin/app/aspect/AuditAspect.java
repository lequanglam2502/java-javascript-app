package com.colin.app.aspect;

import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.colin.app.entity.domain.AuditTrail;
import com.colin.app.entity.repository.AuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditAspect {

	@Autowired
	AuditRepository auditRepository;
	
	@Autowired
	ObjectMapper mapper;

	/**
	 * 
	 * @param joinPoint
	 * @param result
	 * @param auditAction: audit action for all pointcut having @AuditAction annotation
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "@annotation(audit)", returning = "result")
	public void audit(JoinPoint joinPoint, ResponseEntity<?> result, Audit audit) throws Throwable {
		String action = audit.action();
		String tableName = audit.table().getName();
		Object value = result.getBody();

		AuditTrail auditTrail = new AuditTrail();
		auditTrail.setAction(action);
		auditTrail.setName(tableName);
		String response = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(value);
		auditTrail.setValue(response);
		auditTrail.setDate(new Timestamp(System.currentTimeMillis()));

		auditRepository.save(auditTrail);

	}
}
