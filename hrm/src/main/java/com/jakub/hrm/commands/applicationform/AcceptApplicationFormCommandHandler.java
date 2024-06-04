package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmployeeAddress;
import com.jakub.hrm.model.EmploymentSource;
import com.jakub.hrm.repo.ApplicationFormRepo;
import com.jakub.hrm.repo.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcceptApplicationFormCommandHandler {

    ApplicationFormRepo applicationFormRepo;
    EmployeeRepo employeeRepo;
    EmploymentSourceRepo employmentSourceRepo;


    @Autowired
    public AcceptApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo,
                                               EmployeeRepo employeeRepo,
                                               EmploymentSourceRepo employmentSourceRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.employeeRepo = employeeRepo;
        this.employmentSourceRepo = employmentSourceRepo;
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

            Employee employee = new Employee();

            employee.createNewEmployee(applicationForm.getFirstName(), applicationForm.getLastName(),
                    applicationForm.getEmail(), applicationForm.getMobilePhone(), EmploymentStatus.HIRE,
                    positionName, employeeAddress);

            applicationFormRepo.save(applicationForm);
            employeeRepo.save(employee);

            EmploymentSource employmentSource = new EmploymentSource(TypeOfEmploymentSource.APPLICATION,
                    applicationForm, employee);
            employmentSourceRepo.save(employmentSource);
        }
    }
}
