package com.jakub.hrm.repo.formattachment;

import com.jakub.hrm.model.FormAttachment;

import java.sql.SQLException;
import java.util.UUID;

public interface CustomFormAttachmentRepository {
    void saveFormAttachment(FormAttachment file);
    FormAttachment getFormAttachment(UUID id) throws SQLException;
}
