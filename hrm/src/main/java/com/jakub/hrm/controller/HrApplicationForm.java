package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.UpdateDataApplicationFormCommand;
import com.jakub.hrm.commands.applicationform.UpdateDataApplicationFormCommandHandler;
import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.query.applicationform.GetAllApplicationFormsByJobOfferIdQueryHandler;
import com.jakub.hrm.query.applicationform.GetApplicationFormByIdQueryHandler;
import com.jakub.hrm.query.joboffer.GetJobOffersByStatusQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin")
public class HrApplicationForm {

    GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler;
    GetAllApplicationFormsByJobOfferIdQueryHandler getAllApplicationFormsByJobOfferIdQueryHandler;
    GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler;
    UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler;

    @Autowired
    public HrApplicationForm(GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler,
                                GetAllApplicationFormsByJobOfferIdQueryHandler getAllApplicationFormsByJobOfferIdQueryHandler,
                             GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler,
                             UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler) {
        this.getJobOffersByStatusQueryHandler = getJobOffersByStatusQueryHandler;
        this.getAllApplicationFormsByJobOfferIdQueryHandler = getAllApplicationFormsByJobOfferIdQueryHandler;
        this.getApplicationFormByIdQueryHandler = getApplicationFormByIdQueryHandler;
        this.updateApplicationFormCommandHandler = updateApplicationFormCommandHandler;
    }


    @GetMapping("/displayjobofferstoreviewapplications/{display}")
    public String displayJobOffersToServiceApplicationForms(@PathVariable String display, Model model){
        model.addAttribute("jobOffersByStatus", getJobOffersByStatusQueryHandler.Handle(display));
        return "hr/manageapplicationforms";
    }

    @GetMapping("/listofapplicationforms/{jobOfferId}")
    public String displayListOfApplicationFormFromJobOffer(@PathVariable String jobOfferId, Model model) {

        model.addAttribute("applicationFormList", getAllApplicationFormsByJobOfferIdQueryHandler.Handle(jobOfferId));
        return "hr/displaylistofapplicationsonoffer";
    }
    @GetMapping("/managesingleapplicationform/{applicationFormId}")
    public String displaySingleApplicationFormToManage(@PathVariable String applicationFormId, Model model){
        List<String> applicationEmploymentStatusOptions = Arrays.asList(EmploymentStatus.PROCESS, EmploymentStatus.REVIEW, EmploymentStatus.DENY, EmploymentStatus.ACCEPT);

        model.addAttribute("applicationEmploymentStatusOptions", applicationEmploymentStatusOptions);
        model.addAttribute("applicationFormId", applicationFormId);

        model.addAttribute("applicationFormQuery", getApplicationFormByIdQueryHandler.Handle(applicationFormId));

        return "hr/managesingleapplicationform";
    }

    @PostMapping("/updateapplicationform")
    public String updateJobOffer(@Valid @ModelAttribute("applicationFormCommand") UpdateDataApplicationFormCommand updateApplicationFormRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "hr/managesingleapplicationform";
        }
        updateApplicationFormCommandHandler.Handle(updateApplicationFormRequest);
        return "redirect:/admin/listofapplicationforms/" + updateApplicationFormRequest.getJobOfferId();
    }
}
