package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.*;
import com.jakub.hrm.repo.ApplicationFormAuditRepo;
import com.jakub.hrm.repo.HrUserRepo;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import com.jakub.hrm.repo.cvattachment.CvAttachmentRepo;
import com.jakub.hrm.repo.employee.EmployeeAddressRepo;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import com.jakub.hrm.repo.formattachment.FormAttachmentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcceptApplicationFormCommandHandler {

    ApplicationFormRepo applicationFormRepo;
    FormAttachmentRepo formAttachmentRepo;
    EmployeeRepo employeeRepo;
    EmployeeAddressRepo employeeAddressRepo;
    CvAttachmentRepo cvAttachmentRepo;
    EmploymentSourceRepo employmentSourceRepo;
    HrUserRepo hrUserRepo;
    ApplicationFormAuditRepo applicationFormAuditRepo;


    @Autowired
    public AcceptApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo,
                                               FormAttachmentRepo formAttachmentRepo,
                                               EmployeeRepo employeeRepo,
                                               EmployeeAddressRepo employeeAddressRepo,
                                               CvAttachmentRepo cvAttachmentRepo,
                                               EmploymentSourceRepo employmentSourceRepo,
                                               HrUserRepo hrUserRepo,
                                               ApplicationFormAuditRepo applicationFormAuditRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.formAttachmentRepo = formAttachmentRepo;
        this.employeeRepo = employeeRepo;
        this.employeeAddressRepo = employeeAddressRepo;
        this.cvAttachmentRepo = cvAttachmentRepo;
        this.employmentSourceRepo = employmentSourceRepo;
        this.hrUserRepo = hrUserRepo;
        this.applicationFormAuditRepo = applicationFormAuditRepo;
    }

    @Transactional
    public UUID Handle(AcceptApplicationFormCommand command, UUID formAttachmentId, Authentication authentication) throws SQLException {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        HrUser hrUser = hrUserRepo.getByIdentification_Login(authentication.getName());
        FormAttachment formAttachment = formAttachmentRepo.getFormAttachment(formAttachmentId);
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();
            applicationForm.setEmploymentStatus(EmploymentStatus.HIRE);

            CvAttachment cvAttachment = new CvAttachment(UUID.randomUUID(), formAttachment.getFileName(),
                    formAttachment.getFileType(), formAttachment.getData());
            cvAttachmentRepo.saveCvAttachment(cvAttachment);

            EmployeeAddress employeeAddress = new EmployeeAddress(applicationForm.getStreetAddress(),
                    applicationForm.getCity(), applicationForm.getCountry(),
                    applicationForm.getZipCode());
            employeeAddressRepo.save(employeeAddress);

            Employee employee = new Employee();
            UUID employeeId = UUID.randomUUID();

            employee.createNewEmployee(employeeId, applicationForm.getFirstName(), applicationForm.getLastName(),
                    applicationForm.getEmail(), applicationForm.getMobilePhone(), EmploymentStatus.HIRE,
                    applicationForm.getJobOffer().getName(), employeeAddress, cvAttachment);

            employeeRepo.saveEmployee(employee);

            ApplicationFormAudit applicationFormAudit = new ApplicationFormAudit(applicationForm, hrUser);
            applicationFormAuditRepo.save(applicationFormAudit);
            applicationFormRepo.save(applicationForm);

            return employeeId;
        }
        return null;
    }
}
