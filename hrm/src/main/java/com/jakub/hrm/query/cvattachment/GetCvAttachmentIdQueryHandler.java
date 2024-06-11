package com.jakub.hrm.query.cvattachment;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetCvAttachmentIdQueryHandler {

    @Autowired
    EmployeeRepo employeeRepo;

    public UUID Handle(String employeeId) {
        Optional<Employee> dbApplicationForm = employeeRepo.findById(UUID.fromString(employeeId));
        return dbApplicationForm.get().getCv_attachment_id();
    }
}
