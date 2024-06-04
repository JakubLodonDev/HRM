package com.jakub.hrm.repo;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmploymentSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentSourceRepo extends JpaRepository<EmploymentSource, UUID> {

}
