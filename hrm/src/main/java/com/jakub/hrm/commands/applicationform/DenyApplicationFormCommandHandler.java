package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.ApplicationFormAudit;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.ApplicationFormAuditRepo;
import com.jakub.hrm.repo.HrUserRepo;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DenyApplicationFormCommandHandler {
    ApplicationFormRepo applicationFormRepo;
    HrUserRepo hrUserRepo;
    ApplicationFormAuditRepo applicationFormAuditRepo;


    @Autowired
    public DenyApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo,
                                             HrUserRepo hrUserRepo,
                                             ApplicationFormAuditRepo applicationFormAuditRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.hrUserRepo = hrUserRepo;
        this.applicationFormAuditRepo = applicationFormAuditRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command, Authentication authentication) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        HrUser hrUser = hrUserRepo.getByIdentification_Login(authentication.getName());
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();
            applicationForm.setStatusOnDeny();

            ApplicationFormAudit applicationFormAudit = new ApplicationFormAudit(applicationForm, hrUser);
            applicationFormAuditRepo.save(applicationFormAudit);

            applicationFormRepo.save(applicationForm);
        }
    }
}
