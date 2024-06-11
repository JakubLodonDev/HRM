package com.jakub.hrm.query.applicationform;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler {

    JobOfferRepo jobOfferRepo;
    ApplicationFormRepo applicationFormRepo;

    @Autowired
    public GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler(JobOfferRepo jobOfferRepo, ApplicationFormRepo applicationFormRepo) {
        this.jobOfferRepo = jobOfferRepo;
        this.applicationFormRepo = applicationFormRepo;
    }

    public List<ApplicationFormByJobIdQuery> Handle(String jobOfferId, String display) {
        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId));

        List<ApplicationForm> applicationFormList;
        switch (display) {
            case "process":
                applicationFormList = applicationFormRepo.findAllByJobOfferAndEmploymentStatus(jobOffer, EmploymentStatus.PROCESS);
                break;
            case "deny":
                applicationFormList = applicationFormRepo.findAllByJobOfferAndEmploymentStatus(jobOffer, EmploymentStatus.DENY);
                break;
            case "hire":
                applicationFormList = applicationFormRepo.findAllByJobOfferAndEmploymentStatus(jobOffer, EmploymentStatus.HIRE);
                break;
            default:
                applicationFormList = applicationFormRepo.findAllByJobOffer(jobOffer);
                break;
        }

        return duplicateListToDisplay(applicationFormList);
    }

    private List<ApplicationFormByJobIdQuery> duplicateListToDisplay(List<ApplicationForm> applicationFormList) {
        List<ApplicationFormByJobIdQuery> applicationFormQueryList = new ArrayList<>();

        for (ApplicationForm application: applicationFormList) {
            ApplicationFormByJobIdQuery applicationFormQuery = new ApplicationFormByJobIdQuery(application.getApplicationFormId(),
                    application.getFirstName(), application.getLastName(),application.getEmail(), application.getEmploymentStatus());
            applicationFormQueryList.add(applicationFormQuery);
        }
        return applicationFormQueryList;
    }
}
