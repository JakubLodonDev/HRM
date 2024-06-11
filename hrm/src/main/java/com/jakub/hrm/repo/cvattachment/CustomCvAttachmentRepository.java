package com.jakub.hrm.repo.cvattachment;

import com.jakub.hrm.model.CvAttachment;
import com.jakub.hrm.model.FormAttachment;

import java.sql.SQLException;
import java.util.UUID;

public interface CustomCvAttachmentRepository {
    void saveCvAttachment(CvAttachment file);
    CvAttachment getCvAttachment(UUID id) throws SQLException;
}
