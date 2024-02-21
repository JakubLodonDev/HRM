package com.jakub.hrm.controller;

import com.jakub.hrm.commands.joboffer.SaveNewJobOfferCommand;
import com.jakub.hrm.commands.joboffer.SaveNewJobOfferCommandHandler;
import com.jakub.hrm.commands.joboffer.UpdateJobOfferCommand;
import com.jakub.hrm.commands.joboffer.UpdateJobOfferCommandHandler;
import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.query.joboffer.GetAllJobOffersQueryHandler;
import com.jakub.hrm.query.joboffer.GetJobOfferByIdQueryHandler;
import com.jakub.hrm.query.joboffer.GetOpenJobOffersQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("admin")
public class HrJobOfferController {
    GetOpenJobOffersQueryHandler getOpenJobOffersQueryHandler;
    GetAllJobOffersQueryHandler getAllJobOffersQueryHandler;
    GetJobOfferByIdQueryHandler getJobOfferByIdQueryHandler;
    UpdateJobOfferCommandHandler updateJobOfferCommandHandler;
    SaveNewJobOfferCommandHandler saveNewJobOfferCommandHandler;

    @Autowired
    public HrJobOfferController(GetOpenJobOffersQueryHandler jobOfferDisplayQueryHandler,
                                GetAllJobOffersQueryHandler getAllJobOffersQueryHandler,
                                GetJobOfferByIdQueryHandler getJobOfferByIdQueryHandler,
                                UpdateJobOfferCommandHandler updateJobOfferCommandHandler,
                                SaveNewJobOfferCommandHandler saveNewJobOfferCommandHandler) {
        this.getOpenJobOffersQueryHandler = jobOfferDisplayQueryHandler;
        this.getAllJobOffersQueryHandler = getAllJobOffersQueryHandler;
        this.getJobOfferByIdQueryHandler = getJobOfferByIdQueryHandler;
        this.updateJobOfferCommandHandler = updateJobOfferCommandHandler;
        this.saveNewJobOfferCommandHandler = saveNewJobOfferCommandHandler;
    }

    @RequestMapping("/displayjobofferstomanage")
    public String displayAllJobOffersForManagement(Model model){
        model.addAttribute("displayJobOffers", getAllJobOffersQueryHandler.Handle());
        return "hr/managejoboffers";
    }

    @GetMapping("/managesinglejoboffer/{jobOfferId}")
    public String displayDetailsOfSingleJobOffer(@PathVariable String jobOfferId, Model model){
        List<String> jobStatusOptions = Arrays.asList(JobStatus.OPEN, JobStatus.CLOSE);
        model.addAttribute("jobStatusOptions", jobStatusOptions);
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("jobOffer", getJobOfferByIdQueryHandler.Handle(jobOfferId));
        return "hr/managejoboffer";
    }

    @PostMapping("/updatejoboffer")
    public String updateJobOffer(@Valid @ModelAttribute("jobOffer") UpdateJobOfferCommand updateJobOfferRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "hr/managejoboffer";
        }
        updateJobOfferCommandHandler.Handle(updateJobOfferRequest);
        return "redirect:/admin/displayjobofferstomanage";
    }

    @GetMapping("/createjoboffer")
    public String displayCreateJobOfferForm(Model model) {
        List<String> jobStatusOptions = Arrays.asList(JobStatus.OPEN, JobStatus.CLOSE);
        model.addAttribute("jobStatusOptions", jobStatusOptions);
        model.addAttribute("newJobOfferQuery", new SaveNewJobOfferCommand());
        return "hr/createjoboffer";
    }

    @PostMapping("/savenewjoboffer")
    public String createJobOffer(@Valid @ModelAttribute("newJobOffer") SaveNewJobOfferCommand newJobOfferQueryRequest,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "hr/createjoboffer";
        }
        saveNewJobOfferCommandHandler.Handle(newJobOfferQueryRequest);
        return "redirect:/admin/displayjobofferstomanage";
    }
}
