package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.AddressRepo;
import com.jakub.hrm.repo.ApplicationFormRepo;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubmitApplicationCommandHandler {

    ApplicationFormRepo applicationFormRepo;
    JobOfferRepo jobOfferRepo;
    AddressRepo addressRepo;

    @Autowired
    public SubmitApplicationCommandHandler(ApplicationFormRepo applicationFormRepo,
                                           JobOfferRepo jobOfferRepo, AddressRepo addressRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.jobOfferRepo = jobOfferRepo;
        this.addressRepo = addressRepo;
    }

    public SubmitApplicationCommandResult Handle(SubmitApplicationCommand command) {

        var result = Validate(command);
        if (result != null) {
            return result;
        }

        Address address = new Address(command.address.getStreetAddress(), command.address.getCity(),
                command.address.getCountry(), command.address.getZipCode());

        ApplicationForm applicationForm = new ApplicationForm(command.firstName, command.lastName,
                command.email, command.mobilePhone, command.aboutYourself,
                command.employmentStatus, address);

        JobOffer jobOffer = jobOfferRepo.findById(UUID.fromString(command.jobOfferId)).orElse(null);
        applicationForm.setJobOffer(jobOffer);

        addressRepo.save(address);
        applicationForm.setEmploymentStatus(EmploymentStatus.PROCESS);
        applicationFormRepo.save(applicationForm);
        return new SubmitApplicationCommandResult(true);
    }

    private SubmitApplicationCommandResult Validate(SubmitApplicationCommand command) {

        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(command.jobOfferId));
        boolean isApplicationExist = applicationFormRepo.existsByEmailAndJobOffer(command.email, jobOffer);

        if (isApplicationExist) {
            return new SubmitApplicationCommandResult(false, "Osoba o takim emailu juz aplikowala na ta oferte");
        }
        return null;
    }
}
