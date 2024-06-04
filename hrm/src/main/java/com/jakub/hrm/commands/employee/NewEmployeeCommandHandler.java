package com.jakub.hrm.commands.employee;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.*;
import com.jakub.hrm.repo.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewEmployeeCommandHandler {

    EmployeeRepo employeeRepo;
    EmploymentSourceRepo employmentSourceRepo;

    @Autowired
    public NewEmployeeCommandHandler(EmployeeRepo employeeRepo,
                                     EmploymentSourceRepo employmentSourceRepo) {
        this.employeeRepo = employeeRepo;
        this.employmentSourceRepo = employmentSourceRepo;
    }

    public void Handle(NewEmployeeCommand employeeCommand) {

        EmployeeAddress employeeAddress = new EmployeeAddress(employeeCommand.getAddress().getStreetAddress(),
                employeeCommand.getAddress().getCity(), employeeCommand.getAddress().getCountry(), employeeCommand.getAddress().getZipCode());

        Employee employee = new Employee(employeeCommand.getFirstName(), employeeCommand.getLastName(), employeeCommand.getEmail(),
                employeeCommand.getMobilePhone(), EmploymentStatus.HIRE, employeeCommand.getPosition_name(), employeeAddress);

        employeeRepo.save(employee);

        EmploymentSource employmentSource = new EmploymentSource(employeeCommand.getSourceType(),
                null, employee);
        employmentSourceRepo.save(employmentSource);

    }
}
