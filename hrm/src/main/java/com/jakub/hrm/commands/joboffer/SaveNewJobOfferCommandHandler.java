package com.jakub.hrm.commands.joboffer;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveNewJobOfferCommandHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public void Handle(SaveNewJobOfferCommand saveNewJobOfferCommand) {
        JobOffer jobOffer = new JobOffer(saveNewJobOfferCommand.getJobOfferId(),saveNewJobOfferCommand.getName(),
                saveNewJobOfferCommand.getLevel(),saveNewJobOfferCommand.getRequirement(),saveNewJobOfferCommand.getDescription(),
                saveNewJobOfferCommand.getStatus());
        jobOfferRepo.save(jobOffer);
    }
}
