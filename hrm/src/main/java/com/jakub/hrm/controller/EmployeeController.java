package com.jakub.hrm.controller;

import com.jakub.hrm.commands.employee.*;
import com.jakub.hrm.query.constans.GetTypeOfEmploymentSourceQuery;
import com.jakub.hrm.query.cvattachment.GetCvAttachmentIdQueryHandler;
import com.jakub.hrm.query.cvattachment.GetCvAttachmentQuery;
import com.jakub.hrm.query.cvattachment.GetCvAttachmentQueryHandler;
import com.jakub.hrm.query.employee.GetAllHireEmployeeQueryHandle;
import com.jakub.hrm.query.employee.GetEmployeeByIdQueryHandler;
import com.jakub.hrm.query.employeesource.GetEmployeeSourceByEmployeeIdQueryHandler;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    GetAllHireEmployeeQueryHandle getAllHireEmployeeQueryHandle;
    NewEmployeeCommandHandler newEmployeeCommandHandler;
    GetEmployeeByIdQueryHandler getEmployeeByIdQueryHandler;
    GetEmployeeSourceByEmployeeIdQueryHandler getEmployeeSourceByEmployeeIdQueryHandler;
    GetCvAttachmentIdQueryHandler getCvAttachmentIdQueryHandler;
    UpdateEmployeeCommandHandler updateEmployeeCommandHandler;
    FireEmployeeCommandHandler fireEmployeeCommandHandler;
    NewEmploymentSourceCommandHandler newEmploymentSourceCommandHandler;
    GetCvAttachmentQueryHandler getCvAttachmentQueryHandler;

    @Autowired
    public EmployeeController(GetAllHireEmployeeQueryHandle getAllHireEmployeeQueryHandle,
                              NewEmployeeCommandHandler newEmployeeCommandHandler,
                              GetEmployeeByIdQueryHandler getEmployeeByIdQueryHandler,
                              GetEmployeeSourceByEmployeeIdQueryHandler getEmployeeSourceByEmployeeIdQueryHandler,
                              GetCvAttachmentIdQueryHandler getCvAttachmentIdQueryHandler,
                              UpdateEmployeeCommandHandler updateEmployeeCommandHandler,
                              FireEmployeeCommandHandler fireEmployeeCommandHandler,
                              NewEmploymentSourceCommandHandler newEmploymentSourceCommandHandler,
                              GetCvAttachmentQueryHandler getCvAttachmentQueryHandler) {
        this.getAllHireEmployeeQueryHandle = getAllHireEmployeeQueryHandle;
        this.newEmployeeCommandHandler = newEmployeeCommandHandler;
        this.getEmployeeByIdQueryHandler = getEmployeeByIdQueryHandler;
        this.getEmployeeSourceByEmployeeIdQueryHandler = getEmployeeSourceByEmployeeIdQueryHandler;
        this.getCvAttachmentIdQueryHandler = getCvAttachmentIdQueryHandler;
        this.updateEmployeeCommandHandler = updateEmployeeCommandHandler;
        this.fireEmployeeCommandHandler = fireEmployeeCommandHandler;
        this.newEmploymentSourceCommandHandler = newEmploymentSourceCommandHandler;
        this.getCvAttachmentQueryHandler = getCvAttachmentQueryHandler;
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
                             BindingResult bindingResult,@RequestParam("cv") MultipartFile cv, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());
            return "hr/employee/createemployee";
        }

        if (cv.isEmpty()) {
            model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());
            model.addAttribute("errorMessage", "Przesłanie pliku CV jest wymagane.");
            return "hr/employee/createemployee";
        }

        UUID employeeId = newEmployeeCommandHandler.Handle(newEmployeeCommand, cv);

        newEmploymentSourceCommandHandler.Handle(newEmployeeCommand.getSourceType(), employeeId);
        return "redirect:/employee/listofemployee";
    }

    @GetMapping("/employeedetails/{employeeId}")
    public String displayEmployeeDetails(@PathVariable String employeeId, Model model){
        model.addAttribute("typeOfEmploymentSourceOptions", GetTypeOfEmploymentSourceQuery.Handle());

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeQuery", getEmployeeByIdQueryHandler.Handle(employeeId));
        model.addAttribute("employeeSourceQuery", getEmployeeSourceByEmployeeIdQueryHandler.Handle(employeeId));

        model.addAttribute("cvAttachmentId", getCvAttachmentIdQueryHandler.Handle(employeeId));

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

    @GetMapping("/download/{cvAttachmentId}")
    public ResponseEntity<?> downloadFile(@PathVariable UUID cvAttachmentId) throws SQLException {

        GetCvAttachmentQuery cvAttachmentQuery = getCvAttachmentQueryHandler.Handle(cvAttachmentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(cvAttachmentQuery.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cvAttachmentQuery.getFileName() + "\"")
                .body(new ByteArrayResource(cvAttachmentQuery.getData()));
    }
}
