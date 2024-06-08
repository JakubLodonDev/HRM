package com.jakub.hrm.repo.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ApplicationFormRepo extends JpaRepository<ApplicationForm, UUID>, CustomApplicationFormRepository {

    boolean existsByEmailAndJobOffer(String email, Optional<JobOffer> jobOffer);
    List<ApplicationForm> findAllByJobOfferAndEmploymentStatus(Optional<JobOffer> jobOffer, String employmentStatus);
    List<ApplicationForm> findAllByJobOffer(Optional<JobOffer> jobOffer);

}
