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
@RequestMapping("admin")
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

    @RequestMapping("/displayjobofferstomanage")
    public String displayAllJobOffersForManagement(Model model){
        model.addAttribute("displayJobOffers", getAllJobOffersQueryHandler.Handle());
        return "hr/managejoboffers";
    }

    @GetMapping("/managesinglejoboffer/{jobOfferId}")
    public String displayDetailsOfSingleJobOffer(@PathVariable String jobOfferId, Model model){
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("jobOffer", getJobOfferByIdQueryHandler.Handle(jobOfferId));

        model.addAttribute("statusOpen", isJobOfferOpen.Handle(jobOfferId));


        return "hr/managejoboffer";
    }

    @PostMapping(value = "/updatedatajoboffer", params = "action=updateData")
    public String updateJobOffer(@Valid @ModelAttribute("jobOffer") UpdateDataJobOfferCommand updateDataJobOfferRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "hr/managejoboffer";
        }
        updateJobOfferCommandHandler.Handle(updateDataJobOfferRequest);
        return "redirect:/admin/displayjobofferstomanage";
    }

    @PostMapping(value = "/updatedatajoboffer", params = "action=closeOffer")
    public String closeJobOffer(@Valid @ModelAttribute("jobOffer") UpdateDataJobOfferCommand updateDataJobOfferRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "hr/managejoboffer";
        }
        closeJobOfferCommandHandler.Handle(updateDataJobOfferRequest);
        return "redirect:/admin/displayjobofferstomanage";
    }

    @GetMapping("/createjoboffer")
    public String createJobOfferForm(Model model) {
        List<String> jobStatusOptions = Arrays.asList(JobStatus.OPEN, JobStatus.CLOSE);
        model.addAttribute("jobStatusOptions", jobStatusOptions);
        model.addAttribute("newJobOfferCommand", new SaveNewJobOfferCommand());
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
