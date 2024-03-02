package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.AddressRepo;
import com.jakub.hrm.repo.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDataApplicationFormCommandHandler {
    ApplicationFormRepo applicationFormRepo;
    AddressRepo addressRepo;

    @Autowired
    public UpdateDataApplicationFormCommandHandler(ApplicationFormRepo applicationFormRepo, AddressRepo addressRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.addressRepo = addressRepo;
    }

    public void Handle(UpdateDataApplicationFormCommand command) {

        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(command.getApplicationFormId());
        if (dbApplicationForm.isPresent()){
            ApplicationForm applicationForm = dbApplicationForm.get();

            applicationForm.updateData(command.getFirstName(), command.getLastName(), command.getEmail(),
                    command.getMobilePhone(), command.getAboutYourself(), command.getAddress().getStreetAddress(),
                    command.getAddress().getCountry(), command.getAddress().getCity(), command.getAddress().getZipCode());

            //addressRepo.save(address);
            applicationFormRepo.save(applicationForm);
        }
    }

}
