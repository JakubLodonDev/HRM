package com.jakub.hrm.service;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.dto.DisplayJobOffersDTO;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobOfferService {

    @Autowired
    JobOfferRepo jobOfferRepo;

    public List<DisplayJobOffersDTO> getListOfOpenJobOffers() {
        List<JobOffer> jobOffers = jobOfferRepo.findAllByStatus(JobStatus.OPEN);
        List<DisplayJobOffersDTO> displayJobOffersDTOList = new ArrayList<>();
        for (JobOffer job: jobOffers) {
            DisplayJobOffersDTO displayJobOffersDTO = new DisplayJobOffersDTO(job.getJobOfferId(),
                    job.getName(), job.getLevel(), job.getRequirement(), job.getDescription());
            displayJobOffersDTOList.add(displayJobOffersDTO);
        }
        return displayJobOffersDTOList;
    }

    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferRepo.save(jobOffer);
    }

    public void createJobOffer(JobOffer newJobOffer) {
        jobOfferRepo.save(newJobOffer);
    }
}

