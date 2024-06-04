package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.AcceptApplicationFormCommandHandler;
import com.jakub.hrm.commands.applicationform.DenyApplicationFormCommandHandler;
import com.jakub.hrm.commands.applicationform.UpdateDataApplicationFormCommand;
import com.jakub.hrm.commands.applicationform.UpdateDataApplicationFormCommandHandler;
import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.query.applicationform.GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
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
@RequestMapping("applications")
public class HrApplicationFormController {

    GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler;
    GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
    GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler;
    UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler;
    DenyApplicationFormCommandHandler denyApplicationFormCommandHandler;
    AcceptApplicationFormCommandHandler acceptApplicationFormCommandHandler;

    @Autowired
    public HrApplicationFormController(GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler,
                                       GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler,
                                       GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler,
                                       UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler,
                                       DenyApplicationFormCommandHandler denyApplicationFormCommandHandler,
                                       AcceptApplicationFormCommandHandler acceptApplicationFormCommandHandler) {
        this.getJobOffersByStatusQueryHandler = getJobOffersByStatusQueryHandler;
        this.getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler = getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
        this.getApplicationFormByIdQueryHandler = getApplicationFormByIdQueryHandler;
        this.updateApplicationFormCommandHandler = updateApplicationFormCommandHandler;
        this.denyApplicationFormCommandHandler = denyApplicationFormCommandHandler;
        this.acceptApplicationFormCommandHandler = acceptApplicationFormCommandHandler;
    }


    @GetMapping("/listofjobofferstoreviewapplications/{display}")
    public String displayJobOffersToReviewApplicationForms(@PathVariable String display, Model model){
        model.addAttribute("jobOffersByStatus", getJobOffersByStatusQueryHandler.Handle(display));
        return "hr/applicationform/listofjobofferstoreviewapplications";
    }

    @GetMapping("/listofapplicationforms/{display}/{jobOfferId}")
    public String displayListOfApplicationFormFromJobOffer(@PathVariable String display, @PathVariable String jobOfferId, Model model) {
        model.addAttribute("jobOfferId", jobOfferId);
        model.addAttribute("applicationFormList", getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler.Handle(jobOfferId, display));
        return "hr/applicationform/listofapplicationsonoffer";
    }

    @GetMapping("/managesingleapplicationform/{applicationFormId}")
    public String displaySingleApplicationFormToManage(@PathVariable String applicationFormId, Model model){
        List<String> applicationEmploymentStatusOptions = Arrays.asList(EmploymentStatus.PROCESS, EmploymentStatus.DENY, EmploymentStatus.HIRE);

        model.addAttribute("applicationEmploymentStatusOptions", applicationEmploymentStatusOptions);
        model.addAttribute("applicationFormId", applicationFormId);

        model.addAttribute("applicationFormQuery", getApplicationFormByIdQueryHandler.Handle(applicationFormId));

        return "hr/applicationform/managesingleapplicationform";
    }

    @PostMapping(value = "/updateapplicationform", params = "action=updateData")
    public String updateApplicationForm(@Valid @ModelAttribute("applicationFormQuery") UpdateDataApplicationFormCommand updateApplicationFormRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            return "hr/applicationform/managesingleapplicationform";
        }
        updateApplicationFormCommandHandler.Handle(updateApplicationFormRequest);
        return "redirect:/applications/listofapplicationforms/process/" + updateApplicationFormRequest.getJobOfferId();
    }

    @PostMapping(value = "/updateapplicationform", params = "action=denyApplicant")
    public String danyApplicationForm(@Valid @ModelAttribute("applicationFormQuery") UpdateDataApplicationFormCommand updateApplicationFormRequest,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "jobofferdetails";
        }
        denyApplicationFormCommandHandler.Handle(updateApplicationFormRequest);
        return "redirect:/applications/listofapplicationforms/process/" + updateApplicationFormRequest.getJobOfferId();
    }

    @PostMapping(value = "/updateapplicationform", params = "action=hireApplicant")
    public String hireApplicant(@Valid @ModelAttribute("applicationFormQuery") UpdateDataApplicationFormCommand updateApplicationFormRequest,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "jobofferdetails";
        }
        acceptApplicationFormCommandHandler.Handle(updateApplicationFormRequest);
        return "redirect:/applications/listofapplicationforms/process/" + updateApplicationFormRequest.getJobOfferId();
    }
}
