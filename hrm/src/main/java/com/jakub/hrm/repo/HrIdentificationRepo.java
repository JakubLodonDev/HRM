package com.jakub.hrm.repo;

import com.jakub.hrm.model.HrIdentification;
import com.jakub.hrm.model.HrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HrIdentificationRepo extends JpaRepository<HrIdentification, UUID> {
}
