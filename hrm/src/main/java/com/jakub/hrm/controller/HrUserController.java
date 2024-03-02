package com.jakub.hrm.controller;

import com.jakub.hrm.commands.hruser.NewHrUserCommand;
import com.jakub.hrm.query.user.GetAllUsersQueryHandle;
import com.jakub.hrm.query.user.GetHrUserByIdQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class HrUserController {

    GetAllUsersQueryHandle getAllUsersQueryHandle;
    GetHrUserByIdQueryHandler getHrUserByIdQueryHandler;

    @Autowired
    public HrUserController(GetAllUsersQueryHandle getAllUsersQueryHandle,
                            GetHrUserByIdQueryHandler getHrUserByIdQueryHandler) {
        this.getAllUsersQueryHandle = getAllUsersQueryHandle;
        this.getHrUserByIdQueryHandler = getHrUserByIdQueryHandler;
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

    @RequestMapping("/createnewhruser")
    public String createNewHrUser(Model model){
        model.addAttribute("newUserCommand", new NewHrUserCommand());
        return "hr/createhruser";
    }
}
