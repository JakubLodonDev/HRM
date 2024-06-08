package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DenyApplicationFormCommandHandler {
    ApplicationFormRepo applicationFormRepo;


    @Autowired
    public DenyApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo) {
        this.applicationFormRepo = applicationFormRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();

            applicationForm.setStatusOnDeny();

            applicationFormRepo.save(applicationForm);
        }
    }
}
