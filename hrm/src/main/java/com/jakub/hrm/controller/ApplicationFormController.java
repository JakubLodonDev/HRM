package com.jakub.hrm.controller;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.constans.JobStatus;
import com.jakub.hrm.dto.SubmittedApplicationDTO;
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
        model.addAttribute("submittedApplicationDTO", new SubmittedApplicationDTO());
        return "applicationform.html";
    }

    @PostMapping("/saveApplicationForm/{jobOfferId}")
    public String saveApplicationForm(@Valid @ModelAttribute("submittedApplicationDTO") SubmittedApplicationDTO submittedApplicationDTO,
                                       BindingResult bindingResult, @PathVariable String jobOfferId) {

        if (bindingResult.hasErrors()) {
            log.warn("Contact form validation failed due to : " + bindingResult.toString());
            return "applicationform.html";
        }
        submittedApplicationDTO.setEmploymentStatus(EmploymentStatus.PROCESS);
        Address address = new Address(submittedApplicationDTO.address.getStreetAddress(),submittedApplicationDTO.address.getCity(),
                submittedApplicationDTO.address.getCountry(),submittedApplicationDTO.address.getZipCode());

        ApplicationForm applicationForm = new ApplicationForm(submittedApplicationDTO.getFirstName(),submittedApplicationDTO.lastName,
                submittedApplicationDTO.email,submittedApplicationDTO.mobilePhone,submittedApplicationDTO.aboutYourself,
                submittedApplicationDTO.employmentStatus,address);

        if (applicationFormService.isAddressAlreadyApplied(jobOfferId, applicationForm.getEmail())) {
            log.warn("Aplikacja z adresem już istnieje dla tego JobOffer.");
            bindingResult.rejectValue("email", "email.applicationForml", "Osoba o tym adresie e-mail już aplikowała na to stanowisko.");
            return "applicationform.html";
        }

        applicationFormService.saveAddress(applicationForm);
        applicationFormService.saveApplicationForm(applicationForm, jobOfferId);

        return "redirect:/applicationform/" + jobOfferId;
    }
}