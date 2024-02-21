package com.jakub.hrm.controller;

import com.jakub.hrm.query.joboffer.GetJobOffersByStatusQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class HrApplicationForm {

    GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler;
    GetAllApplicationFormsByJobOfferIdQueryHandler getAllApplicationFormsByJobOfferIdQueryHandler;

    @Autowired
    public HrApplicationForm(GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler,
                                GetAllApplicationFormsByJobOfferIdQueryHandler getAllApplicationFormsByJobOfferIdQueryHandler) {
        this.getJobOffersByStatusQueryHandler = getJobOffersByStatusQueryHandler;
        this.getAllApplicationFormsByJobOfferIdQueryHandler = getAllApplicationFormsByJobOfferIdQueryHandler;
    }


    @GetMapping("/displayjobofferstoreviewapplications/{display}")
    public String displayJobOffersToServiceApplicationForms(@PathVariable String display, Model model){
        model.addAttribute("jobOffersByStatus", getJobOffersByStatusQueryHandler.Handle(display));
        return "hr/manageapplicationforms";
    }

    @GetMapping("/listofapplicationforms/{jobOfferId}")
    public String displayListOfApplicationFormFromJobOffer(@PathVariable String jobOfferId, Model model) {

        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("applicationFormList", getAllApplicationFormsByJobOfferIdQueryHandler.Handle(jobOfferId));
        return "hr/managesingleapplicationform";
    }
}
