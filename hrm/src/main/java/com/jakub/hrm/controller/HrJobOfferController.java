package com.jakub.hrm.controller;

import com.jakub.hrm.commands.joboffer.*;
import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.query.joboffer.GetAllJobOffersQueryHandler;
import com.jakub.hrm.query.joboffer.GetJobOfferByIdQueryHandler;
import com.jakub.hrm.query.joboffer.IsJobOfferOpen;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("joboffer")
public class HrJobOfferController {
    GetAllJobOffersQueryHandler getAllJobOffersQueryHandler;
    GetJobOfferByIdQueryHandler getJobOfferByIdQueryHandler;
    UpdateDataJobOfferCommandHandler updateJobOfferCommandHandler;
    SaveNewJobOfferCommandHandler saveNewJobOfferCommandHandler;
    IsJobOfferOpen isJobOfferOpen;
    CloseJobOfferCommandHandler closeJobOfferCommandHandler;

    @Autowired
    public HrJobOfferController(GetAllJobOffersQueryHandler getAllJobOffersQueryHandler,
                                GetJobOfferByIdQueryHandler getJobOfferByIdQueryHandler,
                                UpdateDataJobOfferCommandHandler updateJobOfferCommandHandler,
                                SaveNewJobOfferCommandHandler saveNewJobOfferCommandHandler,
                                IsJobOfferOpen isJobOfferOpen,
                                CloseJobOfferCommandHandler closeJobOfferCommandHandler) {
        this.getAllJobOffersQueryHandler = getAllJobOffersQueryHandler;
        this.getJobOfferByIdQueryHandler = getJobOfferByIdQueryHandler;
        this.updateJobOfferCommandHandler = updateJobOfferCommandHandler;
        this.saveNewJobOfferCommandHandler = saveNewJobOfferCommandHandler;
        this.isJobOfferOpen = isJobOfferOpen;
        this.closeJobOfferCommandHandler = closeJobOfferCommandHandler;
    }

    @RequestMapping("/listofjoboffers")
    public String displayAllJobOffersForManagement(Model model){
        model.addAttribute("displayJobOffers", getAllJobOffersQueryHandler.Handle());
        return "hr/joboffer/listofjoboffers";
    }

    @GetMapping("/jobofferdetails/{jobOfferId}")
    public String displayDetailsOfSingleJobOffer(@PathVariable String jobOfferId, Model model){
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("jobOffer", getJobOfferByIdQueryHandler.Handle(jobOfferId));

        model.addAttribute("statusOpen", isJobOfferOpen.Handle(jobOfferId));

        return "hr/joboffer/jobofferdetails";
    }

    @PostMapping(value = "/updatedatajoboffer", params = "action=updateData")
    public String updateJobOffer(@Valid @ModelAttribute("jobOffer") UpdateDataJobOfferCommand updateDataJobOfferRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "jobofferdetails";
        }
        updateJobOfferCommandHandler.Handle(updateDataJobOfferRequest);
        return "redirect:/joboffer/listofjoboffers";
    }

    @PostMapping(value = "/updatedatajoboffer", params = "action=closeOffer")
    public String closeJobOffer(@Valid @ModelAttribute("jobOffer") UpdateDataJobOfferCommand updateDataJobOfferRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "jobofferdetails";
        }
        closeJobOfferCommandHandler.Handle(updateDataJobOfferRequest);
        return "redirect:/joboffer/listofjoboffers";
    }

    @GetMapping("/createjoboffer")
    public String createJobOfferForm(Model model) {
        List<String> jobStatusOptions = Arrays.asList(JobStatus.OPEN, JobStatus.CLOSE);
        model.addAttribute("jobStatusOptions", jobStatusOptions);
        model.addAttribute("newJobOfferCommand", new SaveNewJobOfferCommand());
        return "hr/joboffer/createjoboffer";
    }

    @PostMapping("/savenewjoboffer")
    public String createJobOffer(@Valid @ModelAttribute("newJobOfferCommand") SaveNewJobOfferCommand newJobOfferQueryRequest,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "hr/joboffer/createjoboffer";
        }
        saveNewJobOfferCommandHandler.Handle(newJobOfferQueryRequest);
        return "redirect:/joboffer/listofjoboffers";
    }
}
