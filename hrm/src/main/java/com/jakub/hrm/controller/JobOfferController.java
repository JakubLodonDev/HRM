package com.jakub.hrm.controller;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.dto.DisplayJobOffersDTO;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import com.jakub.hrm.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobOfferController {

    @Autowired
    JobOfferService jobOfferService;

    @GetMapping("/joboffer")
    public String displayJobOffersPage(Model model){
        List<DisplayJobOffersDTO> displayJobOffersDTOList = jobOfferService.getListOfOpenJobOffers();

        model.addAttribute("jobOffersDTOList", displayJobOffersDTOList);

        return "joboffer.html";
    }
}
