package com.jakub.hrm.commands.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.FormAttachment;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import com.jakub.hrm.repo.formattachment.FormAttachmentRepo;
import com.jakub.hrm.repo.JobOfferRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubmitApplicationCommandHandler {

    ApplicationFormRepo applicationFormRepo;
    JobOfferRepo jobOfferRepo;
    FormAttachmentRepo formAttachmentRepo;

    @Autowired
    public SubmitApplicationCommandHandler(ApplicationFormRepo applicationFormRepo,
                                           JobOfferRepo jobOfferRepo, FormAttachmentRepo formAttachmentRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.jobOfferRepo = jobOfferRepo;
        this.formAttachmentRepo = formAttachmentRepo;
    }

    @Transactional
    public SubmitApplicationCommandResult Handle(SubmitApplicationCommand command, MultipartFile cv) throws IOException, SQLException {

        var result = Validate(command);
        if (result != null) {
            return result;
        }

        FormAttachment attachment = new FormAttachment(UUID.randomUUID(), cv.getOriginalFilename(),
                cv.getContentType(), cv.getBytes());

        formAttachmentRepo.saveFormAttachment(attachment);

        ApplicationForm applicationForm = new ApplicationForm(command.getFirstName(), command.getLastName(),
                command.getEmail(), command.getMobilePhone(), command.getStreetAddress(), command.getCity(),
                command.getCountry(), command.getZipCode(), command.getAboutYourself(),
                command.getEmploymentStatus(), attachment);

        JobOffer jobOffer = jobOfferRepo.findById(command.getJobOfferId()).orElse(null);
        applicationForm.setJobOffer(jobOffer);

        applicationForm.setEmploymentStatus(EmploymentStatus.PROCESS);

        applicationFormRepo.saveForm(applicationForm);
        return new SubmitApplicationCommandResult(true);
    }

    private SubmitApplicationCommandResult Validate(SubmitApplicationCommand command) {

        Optional<JobOffer> jobOffer = jobOfferRepo.findById(command.getJobOfferId());
        boolean isApplicationExist = applicationFormRepo.existsByEmailAndJobOffer(command.getEmail(), jobOffer);

        if (isApplicationExist) {
            return new SubmitApplicationCommandResult(false, "Osoba o takim emailu juz aplikowala na ta oferte");
        }
        return null;
    }
}
