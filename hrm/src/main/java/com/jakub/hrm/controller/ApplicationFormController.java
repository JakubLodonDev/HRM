package com.jakub.hrm.controller;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.service.ApplicationFormService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    @RequestMapping("/applicationform")
    public String displayApplicationPage(Model model){
        model.addAttribute("applicationForm", new ApplicationForm());
        return "applicationform.html";
    }

    @PostMapping("/saveApplicationForm")
    public String saveApplicationForm(@Valid @ModelAttribute("applicationForm") ApplicationForm applicationForm) {
        applicationFormService.saveApplicationForm(applicationForm);
        return "redirect:/applicationform";
    }
}
