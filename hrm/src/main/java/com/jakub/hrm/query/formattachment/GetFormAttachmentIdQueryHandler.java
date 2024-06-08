package com.jakub.hrm.query.formattachment;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetFormAttachmentIdQueryHandler {

    @Autowired
    ApplicationFormRepo applicationFormRepo;

    public UUID Handle(String applicationFormId) {
        Optional<ApplicationForm> dbApplicationForm = applicationFormRepo.findById(UUID.fromString(applicationFormId));
        return dbApplicationForm.get().getForm_attachment_id();
    }
}
