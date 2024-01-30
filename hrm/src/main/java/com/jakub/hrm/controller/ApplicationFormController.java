package com.jakub.hrm.controller;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import com.jakub.hrm.service.ApplicationFormService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;
    private final JobOfferRepo jobOfferRepo;

    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService, JobOfferRepo jobOfferRepo) {
        this.applicationFormService = applicationFormService;
        this.jobOfferRepo = jobOfferRepo;
    }

    @RequestMapping("/applicationform/{jobOfferId}")
    public String displayApplicationPage(@PathVariable String jobOfferId, Model model){
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("applicationForm", new ApplicationForm());
        return "applicationform.html";
    }

    @PostMapping("/saveApplicationForm")
    public String saveApplicationForm(@Valid @ModelAttribute("applicationForm")
                                          ApplicationForm applicationForm, @RequestParam String jobOfferId) {
        JobOffer jobOffer = jobOfferRepo.findById(UUID.fromString(jobOfferId)).orElse(null);
        applicationForm.setJobOffer(jobOffer);
        applicationFormService.saveApplicationForm(applicationForm);
        return "redirect:/joboffer";
    }
}
