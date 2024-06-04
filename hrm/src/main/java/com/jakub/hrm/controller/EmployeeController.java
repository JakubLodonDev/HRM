package com.jakub.hrm.controller;

import com.jakub.hrm.commands.employee.NewEmployeeCommand;
import com.jakub.hrm.commands.employee.NewEmployeeCommandHandler;
import com.jakub.hrm.commands.employee.NewEmploymentSourceCommand;
import com.jakub.hrm.commands.hruser.NewHrUserCommand;
import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.model.EmploymentSource;
import com.jakub.hrm.query.employee.GetAllEmployeeQueryHandle;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    GetAllEmployeeQueryHandle getAllEmployeeQueryHandle;

    NewEmployeeCommandHandler newEmployeeCommandHandler;

    @Autowired
    public EmployeeController(GetAllEmployeeQueryHandle getAllEmployeeQueryHandle,
                              NewEmployeeCommandHandler newEmployeeCommandHandler) {
        this.getAllEmployeeQueryHandle = getAllEmployeeQueryHandle;
        this.newEmployeeCommandHandler = newEmployeeCommandHandler;
    }

    @RequestMapping("/listofemployee")
    public String displayListOfUsers(Model model){
        model.addAttribute("listOfEmployeeQuery", getAllEmployeeQueryHandle.Handle());
        return "hr/employee/listofemployee";
    }

    @RequestMapping("/createnewemployee")
    public String createNewHrUser(Model model){
        List<String> typeOfEmploymentSourceOptions = Arrays.asList(TypeOfEmploymentSource.APPLICATION ,
                TypeOfEmploymentSource.REFERENCE, TypeOfEmploymentSource.OTHER);
        model.addAttribute("typeOfEmploymentSourceOptions", typeOfEmploymentSourceOptions);

        model.addAttribute("newEmployeeCommand", new NewEmployeeCommand());
        return "hr/employee/createemployee";
    }

    @PostMapping("/saveneemployee")
    public String submitForm(
                             @Valid @ModelAttribute("newEmployeeCommand") NewEmployeeCommand newEmployeeCommand,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<String> typeOfEmploymentSourceOptions = Arrays.asList(TypeOfEmploymentSource.APPLICATION ,
                    TypeOfEmploymentSource.REFERENCE, TypeOfEmploymentSource.OTHER);
            model.addAttribute("typeOfEmploymentSourceOptions", typeOfEmploymentSourceOptions);
            return "hr/employee/createemployee";
        }

        newEmployeeCommandHandler.Handle(newEmployeeCommand);
        return "redirect:/employee/listofemployee";
    }
}
