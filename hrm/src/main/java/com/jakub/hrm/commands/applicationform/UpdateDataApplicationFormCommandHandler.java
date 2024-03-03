package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDataApplicationFormCommandHandler {
    ApplicationFormRepo applicationFormRepo;


    @Autowired
    public UpdateDataApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo) {
        this.applicationFormRepo = applicationFormRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();

            applicationForm.updateData(command.getFirstName(), command.getLastName(), command.getEmail(),
                    command.getMobilePhone(), command.getStreetAddress(),
                    command.getCountry(), command.getCity(), command.getZipCode(), command.getAboutYourself());

            applicationFormRepo.save(applicationForm);
        }
    }

}
