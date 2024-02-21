package com.jakub.hrm.repo;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobOfferRepo extends JpaRepository<JobOffer, UUID> {

    List<JobOffer> findAllByStatus(String status);

    List<JobOffer> findAllByOrderByStatusDesc();

    boolean existsByJobOfferId(UUID jobOfferId);
}
