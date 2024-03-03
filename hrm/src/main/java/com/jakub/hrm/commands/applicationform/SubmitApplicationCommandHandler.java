package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
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

    @Autowired
    public SubmitApplicationCommandHandler(ApplicationFormRepo applicationFormRepo,
                                           JobOfferRepo jobOfferRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.jobOfferRepo = jobOfferRepo;
    }

    public SubmitApplicationCommandResult Handle(SubmitApplicationCommand command) {

        var result = Validate(command);
        if (result != null) {
            return result;
        }

        ApplicationForm applicationForm = new ApplicationForm(command.getFirstName(), command.getLastName(),
                command.getEmail(), command.getMobilePhone(), command.getStreetAddress(), command.getCity(),
                command.getCountry(), command.getZipCode(), command.getAboutYourself(),
                command.getEmploymentStatus());

        JobOffer jobOffer = jobOfferRepo.findById(UUID.fromString(command.getJobOfferId())).orElse(null);
        applicationForm.setJobOffer(jobOffer);

        applicationForm.setEmploymentStatus(EmploymentStatus.PROCESS);
        applicationFormRepo.save(applicationForm);
        return new SubmitApplicationCommandResult(true);
    }

    private SubmitApplicationCommandResult Validate(SubmitApplicationCommand command) {

        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(command.getJobOfferId()));
        boolean isApplicationExist = applicationFormRepo.existsByEmailAndJobOffer(command.getEmail(), jobOffer);

        if (isApplicationExist) {
            return new SubmitApplicationCommandResult(false, "Osoba o takim emailu juz aplikowala na ta oferte");
        }
        return null;
    }
}
