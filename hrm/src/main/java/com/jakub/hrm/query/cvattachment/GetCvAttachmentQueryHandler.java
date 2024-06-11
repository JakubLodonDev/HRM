package com.jakub.hrm.query.cvattachment;

import com.jakub.hrm.model.CvAttachment;
import com.jakub.hrm.repo.cvattachment.CvAttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class GetCvAttachmentQueryHandler {

    @Autowired
    CvAttachmentRepo cvAttachmentRepo;

    public GetCvAttachmentQuery Handle(UUID cvAttachmentId) throws SQLException {
        CvAttachment cvAttachment = cvAttachmentRepo.getCvAttachment(cvAttachmentId);
        return new GetCvAttachmentQuery(cvAttachment.getFileName(),
                cvAttachment.getFileType(), cvAttachment.getData());
    }
}
