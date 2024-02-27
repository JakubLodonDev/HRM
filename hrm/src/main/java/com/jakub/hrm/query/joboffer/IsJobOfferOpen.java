package com.jakub.hrm.query.joboffer;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class IsJobOfferOpen {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public boolean Handle(String jobOfferId) {
        Optional<JobOffer> dbJobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId));
        if(dbJobOffer.isPresent()){
            JobOffer jobOffer = dbJobOffer.get();
            return jobOffer.getStatus().equals(JobStatus.OPEN);
        }
        return false;
    }
}
