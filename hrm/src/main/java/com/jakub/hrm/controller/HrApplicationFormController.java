package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.*;
import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.query.applicationform.GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
import com.jakub.hrm.query.applicationform.GetApplicationFormByIdQueryHandler;
import com.jakub.hrm.query.formattachment.GetFormAttachmentIdQueryHandler;
import com.jakub.hrm.query.formattachment.GetFormAttachmentQuery;
import com.jakub.hrm.query.formattachment.GetFormAttachmentQueryHandler;
import com.jakub.hrm.query.joboffer.GetJobOffersByStatusQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("applications")
public class HrApplicationFormController {

    GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler;
    GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
    GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler;
    GetFormAttachmentIdQueryHandler getFormAttachmentIdQueryHandler;
    UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler;
    DenyApplicationFormCommandHandler denyApplicationFormCommandHandler;
    AcceptApplicationFormCommandHandler acceptApplicationFormCommandHandler;
    GetFormAttachmentQueryHandler getFormAttachmentQueryHandler;

    @Autowired
    public HrApplicationFormController(GetJobOffersByStatusQueryHandler getJobOffersByStatusQueryHandler,
                                       GetApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler,
                                       GetApplicationFormByIdQueryHandler getApplicationFormByIdQueryHandler,
                                       GetFormAttachmentIdQueryHandler getFormAttachmentIdQueryHandler,
                                       UpdateDataApplicationFormCommandHandler updateApplicationFormCommandHandler,
                                       DenyApplicationFormCommandHandler denyApplicationFormCommandHandler,
                                       AcceptApplicationFormCommandHandler acceptApplicationFormCommandHandler,
                                       GetFormAttachmentQueryHandler getFormAttachmentQueryHandler) {
        this.getJobOffersByStatusQueryHandler = getJobOffersByStatusQueryHandler;
        this.getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler = getApplicationFormsByJobOfferIdAndEmployeeStatusQueryHandler;
        this.getApplicationFormByIdQueryHandler = getApplicationFormByIdQueryHandler;
        this.getFormAttachmentIdQueryHandler = getFormAttachmentIdQueryHandler;
        this.updateApplicationFormCommandHandler = updateApplicationFormCommandHandler;
        this.denyApplicationFormCommandHandler = denyApplicationFormCommandHandler;
        this.acceptApplicationFormCommandHandler = acceptApplicationFormCommandHandler;
        this.getFormAttachmentQueryHandler = getFormAttachmentQueryHandler;
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

        model.addAttribute("formAttachmentId", getFormAttachmentIdQueryHandler.Handle(applicationFormId));

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

    @GetMapping("/download/{formAttachmentId}")
    public ResponseEntity<?> downloadFile(@PathVariable UUID formAttachmentId) throws SQLException {

        GetFormAttachmentQuery formAttachmentQuery = getFormAttachmentQueryHandler.Handle(formAttachmentId);

        return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(formAttachmentQuery.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + formAttachmentQuery.getFileName() + "\"")
                    .body(new ByteArrayResource(formAttachmentQuery.getData()));
    }
}