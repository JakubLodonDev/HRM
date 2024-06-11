package com.jakub.hrm.commands.employee;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmployeeAddress;
import com.jakub.hrm.repo.employee.EmployeeAddressRepo;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateEmployeeCommandHandler {

    EmployeeRepo employeeRepo;
    EmployeeAddressRepo employeeAddressRepo;

    @Autowired
    public UpdateEmployeeCommandHandler(EmployeeRepo employeeRepo,
                                        EmployeeAddressRepo employeeAddressRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeAddressRepo = employeeAddressRepo;
    }

    public void Handle(UpdateDataEmployeeCommand command) {

        Optional<Employee> dbEmployee = employeeRepo.findById(command.getEmployeeId());
        Optional<EmployeeAddress> dbEmployeeAddress = employeeAddressRepo.findById(dbEmployee.get().getEmployeeAddress().getEmployeeAddressId());
        if (dbEmployeeAddress.isPresent()){
            Employee employee = dbEmployee.get();

            EmployeeAddress employeeAddress = dbEmployeeAddress.get();

            employeeAddress.updateData(command.getAddress().getStreetAddress(),
                    command.getAddress().getCity(), command.getAddress().getCountry(),
                    command.getAddress().getZipCode());

            employee.updateEmployee(command.getFirstName(), command.getLastName(), command.getEmail(),
                    command.getMobilePhone(), command.getPosition_name(), employeeAddress);

            employeeRepo.save(employee);
        }
    }
}
