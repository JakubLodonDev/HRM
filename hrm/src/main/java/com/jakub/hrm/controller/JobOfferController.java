package com.jakub.hrm.controller;

import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JobOfferController {

    @Autowired
    JobOfferRepo jobOfferRepo;

    @GetMapping("/joboffer")
    public String displayJobOffersPage(Model model){

        List<JobOffer> jobOffers = jobOfferRepo.findAll();
        model.addAttribute("jobOffers", jobOffers);

        return "joboffer.html";
    }
}
