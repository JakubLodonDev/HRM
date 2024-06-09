package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.model.*;
import com.jakub.hrm.repo.ApplicationFormAuditRepo;
import com.jakub.hrm.repo.HrUserRepo;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import com.jakub.hrm.repo.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcceptApplicationFormCommandHandler {

    ApplicationFormRepo applicationFormRepo;
    EmployeeRepo employeeRepo;
    EmploymentSourceRepo employmentSourceRepo;

    HrUserRepo hrUserRepo;
    ApplicationFormAuditRepo applicationFormAuditRepo;


    @Autowired
    public AcceptApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo,
                                               EmployeeRepo employeeRepo,
                                               EmploymentSourceRepo employmentSourceRepo,
                                               HrUserRepo hrUserRepo,
                                               ApplicationFormAuditRepo applicationFormAuditRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.employeeRepo = employeeRepo;
        this.employmentSourceRepo = employmentSourceRepo;
        this.hrUserRepo = hrUserRepo;
        this.applicationFormAuditRepo = applicationFormAuditRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command, Authentication authentication) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        HrUser hrUser = hrUserRepo.getByIdentification_Login(authentication.getName());
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

            ApplicationFormAudit applicationFormAudit = new ApplicationFormAudit(applicationForm, hrUser);
            applicationFormAuditRepo.save(applicationFormAudit);

            applicationFormRepo.save(applicationForm);
            employeeRepo.save(employee);

            EmploymentSource employmentSource = new EmploymentSource(TypeOfEmploymentSource.APPLICATION,
                    applicationForm, employee);
            employmentSourceRepo.save(employmentSource);
        }
    }
}
