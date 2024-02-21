package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.SubmitApplicationCommand;
import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandHandler;
import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandResult;
import com.jakub.hrm.query.joboffer.JobOfferExistsQueryHandler;
import com.jakub.hrm.query.joboffer.JobOfferExistsQueryResult;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class ApplicationFormController {
    private final SubmitApplicationCommandHandler submitApplicationCommandHandler;
    private final JobOfferExistsQueryHandler jobOfferExistsQueryHandler;


    @Autowired
    public ApplicationFormController(SubmitApplicationCommandHandler submitApplicationCommandHandler,
                                     JobOfferExistsQueryHandler jobOfferExistsQueryHandler) {
        this.submitApplicationCommandHandler = submitApplicationCommandHandler;
        this.jobOfferExistsQueryHandler = jobOfferExistsQueryHandler;
    }

    @RequestMapping("/applicationform/{jobOfferId}")
    public String displayApplicationPage(@PathVariable String jobOfferId, Model model){

        JobOfferExistsQueryResult result = jobOfferExistsQueryHandler.Handle(jobOfferId);
        if (!result.succeeded) {
            model.addAttribute("errorMessage", result.errorMessage);
            return "applicant/errorpage";
        }

        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("submitApplicationCommand", new SubmitApplicationCommand());
        return "applicant/applicationform";
    }

    @PostMapping("/saveApplicationForm")
    public String saveApplicationForm(@Valid @ModelAttribute("submitApplicationCommand") SubmitApplicationCommand submitApplicationRequest,
                                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("jobOfferId", submitApplicationRequest.getJobOfferId());
            return "applicant/applicationform.html";
        }

        SubmitApplicationCommandResult result = submitApplicationCommandHandler.Handle(submitApplicationRequest);
        if (!result.succeeded){
            model.addAttribute("jobOfferId", submitApplicationRequest.getJobOfferId());
            bindingResult.rejectValue(result.fieldName, result.errorCode, result.errorMessage);
            return "applicant/applicationform.html";
        }

        return "redirect:/applicationform/" + submitApplicationRequest.getJobOfferId();
    }
}