package com.jakub.hrm.repo;

import com.jakub.hrm.model.ApplicationFormAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationFormAuditRepo extends JpaRepository<ApplicationFormAudit, UUID> {
}
