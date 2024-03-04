package com.jakub.hrm.controller;

import com.jakub.hrm.commands.hruser.NewHrCommandHandler;
import com.jakub.hrm.commands.hruser.NewHrUserCommand;
import com.jakub.hrm.query.role.GetAllRolesHandle;
import com.jakub.hrm.query.user.GetAllUsersQueryHandle;
import com.jakub.hrm.query.user.GetHrUserByIdQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin")
public class HrUserController {

    GetAllUsersQueryHandle getAllUsersQueryHandle;
    GetHrUserByIdQueryHandler getHrUserByIdQueryHandler;
    GetAllRolesHandle getAllRolesHandle;
    NewHrCommandHandler newHrCommandHandler;

    @Autowired
    public HrUserController(GetAllUsersQueryHandle getAllUsersQueryHandle,
                            GetHrUserByIdQueryHandler getHrUserByIdQueryHandler,
                            GetAllRolesHandle getAllRolesHandle,
                            NewHrCommandHandler newHrCommandHandler) {
        this.getAllUsersQueryHandle = getAllUsersQueryHandle;
        this.getHrUserByIdQueryHandler = getHrUserByIdQueryHandler;
        this.getAllRolesHandle = getAllRolesHandle;
        this.newHrCommandHandler = newHrCommandHandler;
    }

    @RequestMapping("/displaylistofusers")
    public String displayListOfUsers(Model model){
        model.addAttribute("listOfUsersQuery", getAllUsersQueryHandle.Handle());
        return "hr/listofusers";
    }

    @RequestMapping("/displaydetalishruser/{userId}")
    public String displayDetailsOfUsers(@PathVariable String userId, Model model){
        model.addAttribute("hrUserId", userId);
        model.addAttribute("hrUserQuery", getHrUserByIdQueryHandler.Handle(userId));
        return "hr/detailshruser";
    }

    @RequestMapping("/createnewuser")
    public String createNewHrUser(Model model){
        model.addAttribute("userRoleOptions", getAllRolesHandle.Handle());
        model.addAttribute("newUserCommand", new NewHrUserCommand());
        return "hr/createhruser";
    }

    @PostMapping("/savenewuser")
    public String submitForm(@Valid @ModelAttribute("newUserCommand") NewHrUserCommand newUserRequest,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRoleOptions", getAllRolesHandle.Handle());
            return "hr/createhruser";
        }
        newHrCommandHandler.Handle(newUserRequest);
        return "redirect:/admin/displaylistofusers";
    }
}
