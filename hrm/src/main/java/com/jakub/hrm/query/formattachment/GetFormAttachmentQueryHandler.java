package com.jakub.hrm.query.formattachment;

import com.jakub.hrm.model.FormAttachment;
import com.jakub.hrm.repo.formattachment.FormAttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class GetFormAttachmentQueryHandler {

    @Autowired
    FormAttachmentRepo formAttachmentRepo;

    public GetFormAttachmentQuery Handle(UUID formAttachmentId) throws SQLException {
        FormAttachment formAttachment = formAttachmentRepo.getFormAttachment(formAttachmentId);
        return new GetFormAttachmentQuery(formAttachment.getFileName(),
                formAttachment.getFileType(), formAttachment.getData());
    }
}
