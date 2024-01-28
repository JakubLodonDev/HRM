package com.jakub.hrm.controller;

import com.jakub.hrm.constans.JobLevel;
import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.model.JobOffer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobOfferController {

    @GetMapping("/joboffer")
    public String displayJobOffersPage(Model model){

        List<JobOffer> jobOffers = new ArrayList<>();
        jobOffers.add(new JobOffer(1, "Project Manager", JobLevel.JUNIOR, "Be good", "We want you", JobStatus.OPEN));
        jobOffers.add(new JobOffer(2, "Java Programer", JobLevel.MID, "Be very good", "We want you very much", JobStatus.OPEN));
        model.addAttribute("jobOffers", jobOffers);
        return "joboffer.html";
    }
}
