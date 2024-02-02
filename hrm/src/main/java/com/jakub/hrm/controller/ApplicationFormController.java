package com.jakub.hrm.controller;

import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.repo.JobOfferRepo;
import com.jakub.hrm.service.ApplicationFormService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@Controller
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;


    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;;
    }

    @RequestMapping("/applicationform/{jobOfferId}")
    public String displayApplicationPage(@PathVariable String jobOfferId, Model model){
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("applicationForm", new ApplicationForm());
        return "applicationform.html";
    }

    @PostMapping("/saveApplicationForm/{jobOfferId}")
    public String saveApplicationForm(@Valid @ModelAttribute("applicationForm") ApplicationForm applicationForm,
                                       BindingResult bindingResult, @PathVariable String jobOfferId) {

        if (bindingResult.hasErrors()) {
            log.error("Contact form validation failed due to : " + bindingResult.toString());
            return "applicationform.html";
        }

        if (applicationFormService.isAddressAlreadyApplied(jobOfferId, applicationForm.getEmail())) {
            log.error("Aplikacja z adresem już istnieje dla tego JobOffer.");
            bindingResult.rejectValue("email", "email.applicationForml", "Osoba o tym adresie e-mail już aplikowała na to stanowisko.");
            return "applicationform.html";
        }

        applicationFormService.saveAddress(applicationForm);
        applicationFormService.saveApplicationForm(applicationForm, jobOfferId);
        return "redirect:/applicationform/" + jobOfferId;
    }
}