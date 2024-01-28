package com.jakub.hrm.controller;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.service.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationFormController {


    private ApplicationFormService applicationFormService;

    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    @RequestMapping("/applicationform")
    public String displayApplicationPage(){
        return "applicationform.html";
    }

    @PostMapping("/saveApplicationForm")
    public ModelAndView sendApplicationForm(ApplicationForm applicationForm) {
        applicationFormService.saveApplicationForm(applicationForm);
        return new ModelAndView("redirect:/applicationform");
    }
}
