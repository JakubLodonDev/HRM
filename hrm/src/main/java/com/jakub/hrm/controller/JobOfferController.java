package com.jakub.hrm.controller;

import com.jakub.hrm.query.joboffer.GetOpenJobOffersQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobOfferController {

    @Autowired
    GetOpenJobOffersQueryHandler getOpenJobOffersQueryHandler;

    @GetMapping("/displayjoboffers")
    public String displayJobOffersPage(Model model){
        model.addAttribute("jobOffersDTOList", getOpenJobOffersQueryHandler.Handle());
        return "applicant/joboffers";
    }

}
