package com.jakub.hrm.controller;

import com.jakub.hrm.commands.employee.*;
import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.query.employee.GetAllHireEmployeeQueryHandle;
import com.jakub.hrm.query.employee.GetEmployeeByIdQueryHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    GetAllHireEmployeeQueryHandle getAllHireEmployeeQueryHandle;
    NewEmployeeCommandHandler newEmployeeCommandHandler;
    GetEmployeeByIdQueryHandler getEmployeeByIdQueryHandler;
    UpdateEmployeeCommandHandler updateEmployeeCommandHandler;
    FireEmployeeCommandHandler fireEmployeeCommandHandler;

    @Autowired
    public EmployeeController(GetAllHireEmployeeQueryHandle getAllHireEmployeeQueryHandle,
                              NewEmployeeCommandHandler newEmployeeCommandHandler,
                              GetEmployeeByIdQueryHandler getEmployeeByIdQueryHandler,
                              UpdateEmployeeCommandHandler updateEmployeeCommandHandler,
                              FireEmployeeCommandHandler fireEmployeeCommandHandler) {
        this.getAllHireEmployeeQueryHandle = getAllHireEmployeeQueryHandle;
        this.newEmployeeCommandHandler = newEmployeeCommandHandler;
        this.getEmployeeByIdQueryHandler = getEmployeeByIdQueryHandler;
        this.updateEmployeeCommandHandler = updateEmployeeCommandHandler;
        this.fireEmployeeCommandHandler = fireEmployeeCommandHandler;
    }

    @RequestMapping("/listofemployee")
    public String displayListOfUsers(Model model){
        model.addAttribute("listOfEmployeeQuery", getAllHireEmployeeQueryHandle.Handle());
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

    @GetMapping("/employeedetails/{employeeId}")
    public String displayEmployeeDetails(@PathVariable String employeeId, Model model){

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeQuery", getEmployeeByIdQueryHandler.Handle(employeeId));

        return "hr/employee/employeedetails";
    }

    @PostMapping(value = "/updateemployee", params = "action=updateData")
    public String updateEmployee(@Valid @ModelAttribute("employeeQuery") UpdateDataEmployeeCommand updateEmployeeRequest,
                                        BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "hr/employee/employeedetails";
        }
        updateEmployeeCommandHandler.Handle(updateEmployeeRequest);
        return "redirect:/employee/listofemployee";
    }

    @PostMapping(value = "/updateemployee", params = "action=fireEmployee")
    public String fireEmployee(@Valid @ModelAttribute("employeeQuery") UpdateDataEmployeeCommand updateEmployeeRequest,
                                 BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "hr/employee/employeedetails";
        }
        fireEmployeeCommandHandler.Handle(updateEmployeeRequest);
        return "redirect:/employee/listofemployee";
    }
}
