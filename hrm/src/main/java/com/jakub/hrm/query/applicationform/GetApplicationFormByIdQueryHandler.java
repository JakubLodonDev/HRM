package com.jakub.hrm.query.applicationform;

import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.query.AddressQuery;
import com.jakub.hrm.repo.AddressRepo;
import com.jakub.hrm.repo.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetApplicationFormByIdQueryHandler {

    ApplicationFormRepo applicationFormRepo;

    AddressRepo addressRepo;

    @Autowired
    public GetApplicationFormByIdQueryHandler(ApplicationFormRepo applicationFormRepo, AddressRepo addressRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.addressRepo = addressRepo;
    }

    public ApplicationFormQuery Handle(String applicationFormId) {
        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(UUID.fromString(applicationFormId));
        if(dbApplicationForm.isPresent()) {
            ApplicationForm applicationForm = dbApplicationForm.get();

            AddressQuery addressQuery = new AddressQuery(applicationForm.getAddress().getStreetAddress(),
                    applicationForm.getAddress().getCity(), applicationForm.getAddress().getCountry(),
                    applicationForm.getAddress().getZipCode());

            return new ApplicationFormQuery(applicationForm.getApplicationFormId(),
                    applicationForm.getFirstName(), applicationForm.getLastName(), applicationForm.getEmail(),
                    applicationForm.getMobilePhone(), applicationForm.getAboutYourself(), applicationForm.getEmploymentStatus(),
                    addressQuery, applicationForm.getJobOffer().getJobOfferId());
        }

        return new ApplicationFormQuery();
    }
}

