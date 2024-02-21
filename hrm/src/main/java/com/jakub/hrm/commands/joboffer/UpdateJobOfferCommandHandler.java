package com.jakub.hrm.commands.joboffer;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateJobOfferCommandHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public void Handle(UpdateJobOfferCommand updateJobOfferCommand){
        JobOffer jobOffer = new JobOffer(updateJobOfferCommand.getJobOfferId(), updateJobOfferCommand.getName(),
                updateJobOfferCommand.getLevel(), updateJobOfferCommand.getRequirement(), updateJobOfferCommand.getDescription(),
                updateJobOfferCommand.getStatus());
        jobOfferRepo.save(jobOffer);
    }
}
