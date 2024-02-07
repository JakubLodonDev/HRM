package com.jakub.hrm.service;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.AddressRepo;
import com.jakub.hrm.repo.ApplicationFormRepo;
import com.jakub.hrm.repo.JobOfferRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ApplicationFormService {

    private ApplicationFormRepo applicationFormRepo;
    private JobOfferRepo jobOfferRepo;
    private AddressRepo addressRepo;

    @Autowired
    public ApplicationFormService(ApplicationFormRepo applicationFormRepo, JobOfferRepo jobOfferRepo,
                                  AddressRepo addressRepo) {
        this.applicationFormRepo = applicationFormRepo;
        this.jobOfferRepo = jobOfferRepo;
        this.addressRepo = addressRepo;
    }

    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm, String jobOfferId) {
        JobOffer jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId)).orElse(null);
        applicationForm.setJobOffer(jobOffer);

        applicationForm.setEmploymentStatus(EmploymentStatus.PROCESS);
        return applicationFormRepo.save(applicationForm);
    }

    public boolean isAddressAlreadyApplied(String jobOfferId, String address) {
        JobOffer jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId)).orElse(null);
        Set<ApplicationForm> applicationForms = jobOffer.getApplicationForms();
        return applicationForms.stream()
                .anyMatch(form -> Objects.equals(form.getEmail(), address));
    }

    public Address saveAddress(ApplicationForm applicationForm) {
        return addressRepo.save(applicationForm.getAddress());
    }
}
