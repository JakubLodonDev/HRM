package com.jakub.hrm.controller;

import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.dto.DisplayJobOffersDTO;
import com.jakub.hrm.dto.SubmittedApplicationDTO;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import com.jakub.hrm.service.JobOfferService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
public class JobOfferController {

    JobOfferService jobOfferService;
    JobOfferRepo jobOfferRepo;
    @Autowired
    public JobOfferController(JobOfferService jobOfferService, JobOfferRepo jobOfferRepo) {
        this.jobOfferService = jobOfferService;
        this.jobOfferRepo = jobOfferRepo;
    }

    @GetMapping("/joboffer")
    public String displayJobOffersPage(Model model){
        List<DisplayJobOffersDTO> displayJobOffersDTOList = jobOfferService.getListOfOpenJobOffers();

        model.addAttribute("jobOffersDTOList", displayJobOffersDTOList);

        return "joboffer.html";
    }
    @GetMapping("/managementjoboffers")
    public String displayAllJobOffersForManagement(Model model){
        List<JobOffer> displayJobOffers = jobOfferRepo.findAll();

        model.addAttribute("displayJobOffers", displayJobOffers);

        return "managementjoboffers";
    }
    @GetMapping("/managementjoboffers/{jobOfferId}")
    public String displayDetailsOfSingleJobOffer(@PathVariable String jobOfferId, Model model){
        Optional<JobOffer> jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId));
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("jobOffer", jobOffer);

        return "managementjob.html";
    }

    @PostMapping("/updatejoboffer/{jobOfferId}")
    public String updateJobOffer(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer,
                                 BindingResult bindingResult, @PathVariable String jobOfferId){

        if (bindingResult.hasErrors()) {
            log.warn("Contact form validation failed due to : " + bindingResult.toString());
            return "managementjob.html";
        }

        jobOfferService.updateJobOffer(jobOffer);
        return "redirect:/managementjoboffers";
    }

    @GetMapping("/createjoboffer")
    public String displayCreateJobOfferForm(Model model) {
        List<String> jobStatusOptions = Arrays.asList(JobStatus.OPEN, JobStatus.CLOSE);
        model.addAttribute("jobStatusOptions", jobStatusOptions);
        model.addAttribute("newJobOffer", new JobOffer());
        return "createjoboffer.html";
    }

    @PostMapping("/savenewjoboffer")
    public String createJobOffer(@Valid @ModelAttribute("newJobOffer") JobOffer newJobOffer,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.warn("Contact form validation failed due to : " + bindingResult.toString());
            return "createjoboffer.html";
        }

        jobOfferService.createJobOffer(newJobOffer);
        return "redirect:/managementjoboffers";
    }


}
