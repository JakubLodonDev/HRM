package com.jakub.hrm.query.joboffer;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllJobOffersQueryHandler {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public List<JobOfferQuery> Handle(){
        List<JobOffer> jobOffers = jobOfferRepo.findAllByOrderByStatusDesc();
        List<JobOfferQuery> jobOfferQueryList = duplicateToJobOffers(jobOffers);
        return jobOfferQueryList;
    }

    private List<JobOfferQuery> duplicateToJobOffers(List<JobOffer> jobOffers) {
        List<JobOfferQuery> list = new ArrayList<>();
        for (JobOffer job: jobOffers) {
            JobOfferQuery jobOfferDisplayQuery = new JobOfferQuery(job.getJobOfferId(),
                    job.getName(), job.getLevel(), job.getRequirement(), job.getDescription(), job.getStatus());
            list.add(jobOfferDisplayQuery);
        }
        return list;
    }

}
