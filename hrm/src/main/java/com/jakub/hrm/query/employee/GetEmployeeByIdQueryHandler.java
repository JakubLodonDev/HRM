package com.jakub.hrm.query.employee;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmployeeAddress;
import com.jakub.hrm.query.applicationform.ApplicationFormQuery;
import com.jakub.hrm.repo.EmployeeAddressRepo;
import com.jakub.hrm.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetEmployeeByIdQueryHandler {

    EmployeeRepo employeeRepo;
    EmployeeAddressRepo employeeAddressRepo;

    @Autowired
    public GetEmployeeByIdQueryHandler(EmployeeRepo employeeRepo,
                                       EmployeeAddressRepo employeeAddressRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeAddressRepo = employeeAddressRepo;
    }


    public GetEmployeeByIdQuery Handle(String employeeId) {
        Optional<Employee> dbEmployee = employeeRepo.findById(UUID.fromString(employeeId));
        if(dbEmployee.isPresent()) {
            Employee employee = dbEmployee.get();

            GetEmployeeAddressQuery addressQuery = new GetEmployeeAddressQuery(
                    employee.getEmployeeAddress().getStreetAddress(),
                    employee.getEmployeeAddress().getCity(), employee.getEmployeeAddress().getCountry(),
                    employee.getEmployeeAddress().getZipCode());

            return new GetEmployeeByIdQuery(employee.getFirstName(), employee.getLastName(),
                    employee.getEmail(), employee.getMobilePhone(), employee.getEmploymentStatus(),
                    employee.getPosition_name(), addressQuery);
        }

        return new GetEmployeeByIdQuery();
    }
}
