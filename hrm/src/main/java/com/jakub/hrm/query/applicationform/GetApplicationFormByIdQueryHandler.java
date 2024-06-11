package com.jakub.hrm.query.applicationform;

import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetApplicationFormByIdQueryHandler {

    ApplicationFormRepo applicationFormRepo;

    @Autowired
    public GetApplicationFormByIdQueryHandler(ApplicationFormRepo applicationFormRepo) {
        this.applicationFormRepo = applicationFormRepo;
    }

    public ApplicationFormQuery Handle(String applicationFormId) {
        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(UUID.fromString(applicationFormId));
        if(dbApplicationForm.isPresent()) {
            ApplicationForm applicationForm = dbApplicationForm.get();

            return new ApplicationFormQuery(applicationForm.getApplicationFormId(),
                    applicationForm.getFirstName(), applicationForm.getLastName(), applicationForm.getEmail(),
                    applicationForm.getMobilePhone(), applicationForm.getStreetAddress(), applicationForm.getCity(), applicationForm.getCountry(),
                    applicationForm.getZipCode(), applicationForm.getAboutYourself(), applicationForm.getEmploymentStatus(),
                     applicationForm.getJobOffer().getJobOfferId());
        }

        return new ApplicationFormQuery();
    }
}

