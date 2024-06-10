package com.jakub.hrm.controller;

import com.jakub.hrm.commands.hruser.*;
import com.jakub.hrm.query.role.GetAllRolesHandle;
import com.jakub.hrm.query.hruser.GetAllUsersQueryHandle;
import com.jakub.hrm.query.hruser.GetHrUserByIdQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class HrUserAdminController {

    GetAllUsersQueryHandle getAllUsersQueryHandle;
    GetHrUserByIdQueryHandler getHrUserByIdQueryHandler;
    UpdateDataHrUserCommandHandler updateDataHrUserHandler;
    ResetPasswordHrUserCommandHandler resetPasswordHrUserHandler;
    DeleteUserCommandHandler deleteUserCommandHandler;
    GetAllRolesHandle getAllRolesHandle;
    NewHrCommandHandler newHrCommandHandler;

    @Autowired
    public HrUserAdminController(GetAllUsersQueryHandle getAllUsersQueryHandle,
                                 GetHrUserByIdQueryHandler getHrUserByIdQueryHandler,
                                 UpdateDataHrUserCommandHandler updateDataHrUserHandler,
                                 ResetPasswordHrUserCommandHandler resetPasswordHrUserHandler,
                                 DeleteUserCommandHandler deleteUserCommandHandler,
                                 GetAllRolesHandle getAllRolesHandle,
                                 NewHrCommandHandler newHrCommandHandler) {
        this.getAllUsersQueryHandle = getAllUsersQueryHandle;
        this.getHrUserByIdQueryHandler = getHrUserByIdQueryHandler;
        this.updateDataHrUserHandler = updateDataHrUserHandler;
        this.resetPasswordHrUserHandler = resetPasswordHrUserHandler;
        this.deleteUserCommandHandler = deleteUserCommandHandler;
        this.getAllRolesHandle = getAllRolesHandle;
        this.newHrCommandHandler = newHrCommandHandler;
    }

    @RequestMapping("/listofusers")
    public String displayListOfUsers(Model model){
        model.addAttribute("listOfUsersQuery", getAllUsersQueryHandle.Handle());
        return "hr/user/listofusers";
    }

    @RequestMapping("/userdetails/{userId}")
    public String displayDetailsOfUsers(@PathVariable String userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("userRoleOptions", getAllRolesHandle.Handle());
        model.addAttribute("hrUserQuery", getHrUserByIdQueryHandler.Handle(userId));
        return "hr/user/userdetails";
    }

    @PostMapping(value = "/updateuser", params = "action=updateData")
    public String updateUser(@Valid @ModelAttribute("hrUserQuery") UpdateDataHrUserCommand updateDataHrUserRequest,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "userdetails";
        }
        updateDataHrUserHandler.Handle(updateDataHrUserRequest);
        return "redirect:/user/listofusers";
    }

    @PostMapping(value = "/updateuser", params = "action=resetPassword")
    public String resetPasswordForUser(@ModelAttribute("userId") String userId){
        resetPasswordHrUserHandler.Handle(userId);
        return "redirect:/user/listofusers";
    }

    @PostMapping(value = "/updateuser", params = "action=delete")
    public String deleteUser(@ModelAttribute("userId") String userId){
        deleteUserCommandHandler.Handle(userId);
        return "redirect:/user/listofusers";
    }

    @RequestMapping("/createnewuser")
    public String createNewHrUser(Model model){
        model.addAttribute("userRoleOptions", getAllRolesHandle.Handle());
        model.addAttribute("newUserCommand", new NewHrUserCommand());
        return "hr/user/createhruser";
    }

    @PostMapping("/savenewuser")
    public String submitForm(@Valid @ModelAttribute("newUserCommand") NewHrUserCommand newUserRequest,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRoleOptions", getAllRolesHandle.Handle());
            return "hr/user/createhruser";
        }
        newHrCommandHandler.Handle(newUserRequest);
        return "redirect:/user/listofusers";
    }
}
