package com.jakub.hrm.controller;

import com.jakub.hrm.commands.employee.*;
import com.jakub.hrm.constans.TypeOfEmploymentSource;
import com.jakub.hrm.query.constans.GetTypeOfEmploymentSourceQuery;
import com.jakub.hrm.query.employee.GetAllHireEmployeeQueryHandle;
import com.jakub.hrm.query.employee.GetEmployeeByIdQueryHandler;
import com.jakub.hrm.query.employeesource.GetEmployeeSourceByEmployeeIdQueryHandler;
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
    GetEmployeeSourceByEmployeeIdQueryHandler getEmployeeSourceByEmployeeIdQueryHandler;
    UpdateEmployeeCommandHandler updateEmployeeCommandHandler;
    FireEmployeeCommandHandler fireEmployeeCommandHandler;

    @Autowired
    public EmployeeController(GetAllHireEmployeeQueryHandle getAllHireEmployeeQueryHandle,
                              NewEmployeeCommandHandler newEmployeeCommandHandler,
                              GetEmployeeByIdQueryHandler getEmployeeByIdQueryHandler,
                              GetEmployeeSourceByEmployeeIdQueryHandler getEmployeeSourceByEmployeeIdQueryHandler,
                              UpdateEmployeeCommandHandler updateEmployeeCommandHandler,
                              FireEmployeeCommandHandler fireEmployeeCommandHandler) {
        this.getAllHireEmployeeQueryHandle = getAllHireEmployeeQueryHandle;
        this.newEmployeeCommandHandler = newEmployeeCommandHandler;
        this.getEmployeeByIdQueryHandler = getEmployeeByIdQueryHandler;
        this.getEmployeeSourceByEmployeeIdQueryHandler = getEmployeeSourceByEmployeeIdQueryHandler;
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

        model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());
        model.addAttribute("newEmployeeCommand", new NewEmployeeCommand());
        return "hr/employee/createemployee";
    }

    @PostMapping("/saveneemployee")
    public String submitNewEmployee(
                             @Valid @ModelAttribute("newEmployeeCommand") NewEmployeeCommand newEmployeeCommand,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());
            return "hr/employee/createemployee";
        }

        newEmployeeCommandHandler.Handle(newEmployeeCommand);
        return "redirect:/employee/listofemployee";
    }

    @GetMapping("/employeedetails/{employeeId}")
    public String displayEmployeeDetails(@PathVariable String employeeId, Model model){
        model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeQuery", getEmployeeByIdQueryHandler.Handle(employeeId));
        model.addAttribute("employeeSourceQuery", getEmployeeSourceByEmployeeIdQueryHandler.Handle(employeeId));

        return "hr/employee/employeedetails";
    }

    @PostMapping(value = "/updateemployee", params = "action=updateData")
    public String updateEmployee(@Valid @ModelAttribute("employeeQuery") UpdateDataEmployeeCommand updateEmployeeRequest,
                                        BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", updateEmployeeRequest.getEmployeeId());
            model.addAttribute("employeeSourceQuery",
                    getEmployeeSourceByEmployeeIdQueryHandler.Handle(String.valueOf(updateEmployeeRequest.getEmployeeId())));
            return "hr/employee/employeedetails";
        }
        updateEmployeeCommandHandler.Handle(updateEmployeeRequest);
        return "redirect:/employee/listofemployee";
    }

    @PostMapping(value = "/updateemployee", params = "action=fireEmployee")
    public String fireEmployee(@Valid @ModelAttribute("employeeQuery") UpdateDataEmployeeCommand updateEmployeeRequest,
                                 BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", updateEmployeeRequest.getEmployeeId());
            model.addAttribute("employeeSourceQuery",
                    getEmployeeSourceByEmployeeIdQueryHandler.Handle(String.valueOf(updateEmployeeRequest.getEmployeeId())));
            return "hr/employee/employeedetails";
        }
        fireEmployeeCommandHandler.Handle(updateEmployeeRequest);
        return "redirect:/employee/listofemployee";
    }
}
