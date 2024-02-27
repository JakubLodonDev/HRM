package com.jakub.hrm.commands.joboffer;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CloseJobOfferCommandHandler {
    @Autowired
    JobOfferRepo jobOfferRepo;

    public void Handle(UpdateDataJobOfferCommand command) {
        Optional<JobOffer> dbJobOffer = jobOfferRepo.findById(UUID.fromString(String.valueOf(command.getJobOfferId())));
        if(dbJobOffer.isPresent()){
            JobOffer jobOffer = dbJobOffer.get();
            jobOffer.closeJobOffer();
            jobOfferRepo.save(jobOffer);
        }
    }
}
