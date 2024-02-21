package com.jakub.hrm.query.joboffer;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GetJobOffersByStatusQueryHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public List<JobOfferQuery> Handle(String display){
        List<JobOffer> jobOffers;
        if(Objects.equals(display, "open")){
            jobOffers = jobOfferRepo.findAllByStatus(JobStatus.OPEN);
        }
        else if(Objects.equals(display, "close")){
            jobOffers = jobOfferRepo.findAllByStatus(JobStatus.CLOSE);
        }
        else {
            jobOffers = jobOfferRepo.findAll();
        }

        return duplicateListToDisplay(jobOffers);
    }

    private List<JobOfferQuery> duplicateListToDisplay(List<JobOffer> jobOffers) {
        List<JobOfferQuery> list = new ArrayList<>();
        for (JobOffer job: jobOffers) {
            JobOfferQuery jobOfferDisplayQuery = new JobOfferQuery(job.getJobOfferId(),
                    job.getName(), job.getLevel(), job.getRequirement(), job.getDescription(), job.getStatus());
            list.add(jobOfferDisplayQuery);
        }
        return list;
    }

}
