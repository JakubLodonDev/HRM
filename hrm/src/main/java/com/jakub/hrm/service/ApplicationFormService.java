package com.jakub.hrm.service;

import com.jakub.hrm.model.ApplicationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationFormService {
    public void saveApplicationForm(ApplicationForm applicationForm) {
        log.info(applicationForm.toString());
    }
}
