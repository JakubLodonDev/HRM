package com.jakub.hrm.query.employeesource;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmploymentSource;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetEmployeeSourceByEmployeeIdQueryHandler {

    EmployeeRepo employeeRepo;
    EmploymentSourceRepo employmentSourceRepo;

    @Autowired
    public GetEmployeeSourceByEmployeeIdQueryHandler(EmployeeRepo employeeRepo, EmploymentSourceRepo employmentSourceRepo) {
        this.employeeRepo = employeeRepo;
        this.employmentSourceRepo = employmentSourceRepo;
    }

    public GetEmployeeSourceByEmployeeIdQuery Handle(String employeeId) {
        Optional<Employee> dbEmployee = employeeRepo.findById(UUID.fromString(employeeId));
        if(dbEmployee.isPresent()) {
            Employee employee = dbEmployee.get();
            EmploymentSource employmentSource = employmentSourceRepo.getByEmployee(employee);
            return new GetEmployeeSourceByEmployeeIdQuery(employmentSource.getSourceType());
        }
        return new GetEmployeeSourceByEmployeeIdQuery();
    }
}
