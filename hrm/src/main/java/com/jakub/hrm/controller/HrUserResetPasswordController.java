package com.jakub.hrm.controller;

import com.jakub.hrm.commands.hruser.NewHrIdentificationCommand;
import com.jakub.hrm.commands.hruser.ResetNewPasswordCommandHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HrUserResetPasswordController {

    @Autowired
    ResetNewPasswordCommandHandler resetNewPasswordCommandHandler;

    @GetMapping("/resetpassword")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("newPassword", new NewHrIdentificationCommand());
        return "hr/resetpassword";
    }

    @PostMapping("/savenewpassword")
    public String changeUserPassword(@Valid @ModelAttribute("newPassword") NewHrIdentificationCommand newPasswordRequest,
                                     BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "hr/resetpassword";
        }
        resetNewPasswordCommandHandler.Handle(newPasswordRequest, authentication);
        return "redirect:/dashboard";
    }
}
