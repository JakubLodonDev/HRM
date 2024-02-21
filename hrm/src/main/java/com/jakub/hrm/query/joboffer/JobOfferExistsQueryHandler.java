package com.jakub.hrm.query.joboffer;

import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobOfferExistsQueryHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public JobOfferExistsQueryResult Handle(String jobOfferId){
        try {
            UUID validUuid = UUID.fromString(jobOfferId);
        } catch (IllegalArgumentException e) {
            return new JobOfferExistsQueryResult(false, "Nieprawidłowy format identyfikatora oferty pracy.");
        }

        if(!jobOfferIdExist(jobOfferId)){
            return new JobOfferExistsQueryResult(false, "Takiej oferty pracy nie ma");
        }
        return new JobOfferExistsQueryResult(true);
    }

    public boolean jobOfferIdExist(String jobOfferId){
        return jobOfferRepo.existsByJobOfferId(UUID.fromString(jobOfferId));
    }
}
