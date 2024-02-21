package com.jakub.hrm.query.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.ApplicationFormRepo;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetAllApplicationFormsByJobOfferIdQueryHandler {

    JobOfferRepo jobOfferRepo;
    ApplicationFormRepo applicationFormRepo;

    @Autowired
    public GetAllApplicationFormsByJobOfferIdQueryHandler(JobOfferRepo jobOfferRepo, ApplicationFormRepo applicationFormRepo) {
        this.jobOfferRepo = jobOfferRepo;
        this.applicationFormRepo = applicationFormRepo;
    }

    public List<ApplicationFormQuery> Handle(String jobOfferId) {
        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId));

        List<ApplicationForm> applicationFormList = applicationFormRepo.findAllByJobOffer(jobOffer);

        return duplicateListToDisplay(applicationFormList);
    }

    private List<ApplicationFormQuery> duplicateListToDisplay(List<ApplicationForm> applicationFormList) {
        List<ApplicationFormQuery> applicationFormQueryList = new ArrayList<>();
        for (ApplicationForm application: applicationFormList) {
            ApplicationFormQuery applicationFormQuery = new ApplicationFormQuery(application.getApplicationFormId(),application.getFirstName(),
                    application.getLastName(),application.getEmail(),application.getMobilePhone(),application.getAboutYourself(),
                    application.getEmploymentStatus());
            applicationFormQueryList.add(applicationFormQuery);
        }
        return applicationFormQueryList;
    }
}
