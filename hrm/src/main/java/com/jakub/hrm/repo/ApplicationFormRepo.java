package com.jakub.hrm.repo;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ApplicationFormRepo extends JpaRepository<ApplicationForm, UUID> {

    boolean existsByEmailAndJobOffer(String email, Optional<JobOffer> jobOffer);
    List<ApplicationForm> findAllByJobOffer(Optional<JobOffer> jobOffer);

}
