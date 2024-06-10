package com.jakub.hrm.controller;

import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandResult;
import com.jakub.hrm.commands.hruser.NewHrIdentificationCommand;
import com.jakub.hrm.commands.hruserprofile.ChangeNewPasswordCommandHandler;
import com.jakub.hrm.commands.hruserprofile.CheckPasswordCommandResult;
import com.jakub.hrm.commands.hruserprofile.UpdateDataHrUserProfileCommand;
import com.jakub.hrm.commands.hruserprofile.UpdateDataHrUserProfileCommandHandler;
import com.jakub.hrm.query.hruserprofile.GetCurrentHrUserPasswordByIdQuery;
import com.jakub.hrm.query.hruserprofile.GetCurrentHrUserPasswordByIdQueryHandler;
import com.jakub.hrm.query.hruserprofile.GetHrUserProfileByIdQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
public class HrUserProfileController {

    GetHrUserProfileByIdQueryHandler getHrUserProfileByIdQueryHandler;
    UpdateDataHrUserProfileCommandHandler updateDataHrUserProfileCommandHandler;
    GetCurrentHrUserPasswordByIdQueryHandler getCurrentHrUserPasswordByIdQueryHandler;
    ChangeNewPasswordCommandHandler changeNewPasswordCommandHandler;

    @Autowired
    public HrUserProfileController(GetHrUserProfileByIdQueryHandler getHrUserProfileByIdQueryHandler,
                                   UpdateDataHrUserProfileCommandHandler updateDataHrUserProfileCommandHandler,
                                   GetCurrentHrUserPasswordByIdQueryHandler getCurrentHrUserPasswordByIdQueryHandler,
                                   ChangeNewPasswordCommandHandler changeNewPasswordCommandHandler) {
        this.getHrUserProfileByIdQueryHandler = getHrUserProfileByIdQueryHandler;
        this.updateDataHrUserProfileCommandHandler = updateDataHrUserProfileCommandHandler;
        this.getCurrentHrUserPasswordByIdQueryHandler = getCurrentHrUserPasswordByIdQueryHandler;
        this.changeNewPasswordCommandHandler = changeNewPasswordCommandHandler;
    }

    @RequestMapping("/displayprofile/{hrUserId}")
    public String displayDetailsOfUsers(@PathVariable String hrUserId, Model model){
        model.addAttribute("hrUserId", hrUserId);
        model.addAttribute("hrUserProfileQuery", getHrUserProfileByIdQueryHandler.Handle(hrUserId));
        return "hr/userprofile/userprofiledetails";
    }

    @PostMapping(value = "/updateuserprofile", params = "action=updateData")
    public String updateUser(@Valid @ModelAttribute("hrUserProfileQuery")
                                 UpdateDataHrUserProfileCommand updateDataHrUserProfileRequest,
                             BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("hrUserId", updateDataHrUserProfileRequest.getUserId());
            return "hr/userprofile/userprofiledetails";
        }
        updateDataHrUserProfileCommandHandler.Handle(updateDataHrUserProfileRequest);
        return "redirect:/logout";
    }

    @RequestMapping("/changepassword/{hrUserId}")
    public String changePassword(@PathVariable String hrUserId, Model model){
        model.addAttribute("hrUserId", hrUserId);
        //model.addAttribute("newPassword", new NewHrIdentificationCommand());
        model.addAttribute("newPasswordQuery", new GetCurrentHrUserPasswordByIdQuery());
        return "hr/userprofile/changepassword";
    }

    @PostMapping("/savenewpassword")
    public String saveNewHrUserPassword(@Valid @ModelAttribute("newPasswordQuery") GetCurrentHrUserPasswordByIdQuery newPasswordQuery,
                                        //@Valid @ModelAttribute("newPassword") NewHrIdentificationCommand newPasswordRequest,

                                     BindingResult bindingResult, Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            return "hr/userprofile/changepassword";
        }

        CheckPasswordCommandResult result = changeNewPasswordCommandHandler.Handle(newPasswordQuery, authentication);
        if (!result.succeeded){
            model.addAttribute(result.fieldName, result.errorMessage);
            return "hr/userprofile/changepassword";
        }

        return "redirect:/logout";
    }

}
