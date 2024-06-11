package com.jakub.hrm.commands.employee;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.*;
import com.jakub.hrm.repo.cvattachment.CvAttachmentRepo;
import com.jakub.hrm.repo.employee.EmployeeAddressRepo;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class NewEmployeeCommandHandler {

    EmployeeRepo employeeRepo;
    EmployeeAddressRepo employeeAddressRepo;
    EmploymentSourceRepo employmentSourceRepo;
    CvAttachmentRepo cvAttachmentRepo;

    @Autowired
    public NewEmployeeCommandHandler(EmployeeRepo employeeRepo,
                                     EmployeeAddressRepo employeeAddressRepo,
                                     EmploymentSourceRepo employmentSourceRepo,
                                     CvAttachmentRepo cvAttachmentRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeAddressRepo = employeeAddressRepo;
        this.employmentSourceRepo = employmentSourceRepo;
        this.cvAttachmentRepo = cvAttachmentRepo;
    }

    @Transactional
    public UUID Handle(NewEmployeeCommand employeeCommand, MultipartFile cv) throws IOException {

        CvAttachment cvAttachment = new CvAttachment(UUID.randomUUID(), cv.getOriginalFilename(),
                cv.getContentType(), cv.getBytes());
        cvAttachmentRepo.saveCvAttachment(cvAttachment);


        EmployeeAddress employeeAddress = new EmployeeAddress(employeeCommand.getAddress().getStreetAddress(),
                employeeCommand.getAddress().getCity(), employeeCommand.getAddress().getCountry(),
                employeeCommand.getAddress().getZipCode());

        employeeAddressRepo.save(employeeAddress);

        UUID employeeId = UUID.randomUUID();

        Employee employee = new Employee(employeeId, employeeCommand.getFirstName(), employeeCommand.getLastName(),
                employeeCommand.getEmail(), employeeCommand.getMobilePhone(), EmploymentStatus.HIRE,
                employeeCommand.getPosition_name(), employeeAddress, cvAttachment);

        employeeRepo.saveEmployee(employee);

        return employeeId;
    }
}
