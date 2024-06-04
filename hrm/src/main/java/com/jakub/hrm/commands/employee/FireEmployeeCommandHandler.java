package com.jakub.hrm.commands.employee;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FireEmployeeCommandHandler {

    EmployeeRepo employeeRepo;


    @Autowired
    public FireEmployeeCommandHandler(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void Handle(UpdateDataEmployeeCommand command) {

        Optional<Employee> dbEmployee = employeeRepo.findById(command.getEmployeeId());
        if (dbEmployee.isPresent()){
            Employee employee = dbEmployee.get();

            employee.setEmploymentStatusOnFire();

            employeeRepo.save(employee);
        }
    }
}
