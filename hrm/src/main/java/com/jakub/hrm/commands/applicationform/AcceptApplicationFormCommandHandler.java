package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmployeeAddress;
import com.jakub.hrm.repo.ApplicationFormRepo;
import com.jakub.hrm.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcceptApplicationFormCommandHandler {

    ApplicationFormRepo applicationFormRepo;

    EmployeeRepo employeeRepo;


    @Autowired
    public AcceptApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo, EmployeeRepo employeeRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.employeeRepo = employeeRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();

            applicationForm.setEmploymentStatus(EmploymentStatus.HIRE);

            EmployeeAddress employeeAddress = new EmployeeAddress(applicationForm.getStreetAddress(),
                    applicationForm.getCity(), applicationForm.getCountry(),
                    applicationForm.getZipCode());

            String positionName = applicationForm.getJobOffer().getName();

            Employee employee = new Employee(applicationForm.getFirstName(), applicationForm.getLastName(),
                    applicationForm.getEmail(), applicationForm.getMobilePhone(), EmploymentStatus.HIRE,
                    positionName, employeeAddress);

            applicationFormRepo.save(applicationForm);
            employeeRepo.save(employee);
        }
    }
}
