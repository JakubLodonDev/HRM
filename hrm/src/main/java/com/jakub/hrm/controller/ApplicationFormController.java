package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.SubmitApplicationCommand;
import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandHandler;
import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandResult;
import com.jakub.hrm.model.FormAttachment;
import com.jakub.hrm.query.joboffer.JobOfferExistsQueryHandler;
import com.jakub.hrm.query.joboffer.JobOfferExistsQueryResult;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

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
    public String saveApplicationForm(@Valid @ModelAttribute("submitApplicationCommand")
                                          SubmitApplicationCommand submitApplicationRequest,
                                      BindingResult bindingResult, @RequestParam("cv") MultipartFile cv,
                                      Model model) throws IOException, SQLException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("jobOfferId", submitApplicationRequest.getJobOfferId());
            return "applicant/applicationform";
        }

        if (cv.isEmpty()) {
            model.addAttribute("jobOfferId", submitApplicationRequest.getJobOfferId());
            model.addAttribute("errorMessage", "Przesłanie pliku CV jest wymagane.");
            return "applicant/applicationform";
        }

        SubmitApplicationCommandResult result = submitApplicationCommandHandler.Handle(submitApplicationRequest, cv);

        if (!result.succeeded){
            model.addAttribute("jobOfferId", submitApplicationRequest.getJobOfferId());
            bindingResult.rejectValue(result.fieldName, result.errorCode, result.errorMessage);
            return "applicant/applicationform";
        }

        return "redirect:/applicationform/" + submitApplicationRequest.getJobOfferId();
    }
}