package com.jakub.hrm.commands.joboffer;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDataJobOfferCommandHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public void Handle(UpdateDataJobOfferCommand command){

        Optional<JobOffer> dbJobOffer = jobOfferRepo.findById(command.getJobOfferId());
        if(dbJobOffer.isPresent()){
            JobOffer jobOffer = dbJobOffer.get();
            jobOffer.updateData(command.getName(),
                    command.getLevel(), command.getRequirement(), command.getDescription());
            jobOfferRepo.save(jobOffer);
        }
    }
}
