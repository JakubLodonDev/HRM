package com.jakub.hrm.repo.employee;

import com.jakub.hrm.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID>, CustomEmployeeRepository {
    List<Employee> findAllByEmploymentStatus(String employmentStatus);
}
