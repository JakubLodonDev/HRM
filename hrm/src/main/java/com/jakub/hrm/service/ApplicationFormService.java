package com.jakub.hrm.service;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.ApplicationFormRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ApplicationFormService {

    @Autowired
    ApplicationFormRepo applicationFormRepo;

    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm) {
        applicationForm.setEmploymentStatus(EmploymentStatus.PROCESS);
        return applicationFormRepo.save(applicationForm);
    }
}
