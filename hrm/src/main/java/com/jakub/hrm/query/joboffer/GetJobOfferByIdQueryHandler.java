package com.jakub.hrm.query.joboffer;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetJobOfferByIdQueryHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public JobOfferQuery Handle(String jobOfferId){
        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId));
        return new JobOfferQuery(jobOffer.get().getJobOfferId(), jobOffer.get().getName(),
                jobOffer.get().getLevel(), jobOffer.get().getRequirement(), jobOffer.get().getDescription(),
                jobOffer.get().getStatus());
    }
}
