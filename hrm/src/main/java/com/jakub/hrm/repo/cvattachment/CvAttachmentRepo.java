package com.jakub.hrm.repo.cvattachment;

import com.jakub.hrm.model.CvAttachment;
import com.jakub.hrm.repo.cvattachment.CustomCvAttachmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CvAttachmentRepo extends JpaRepository<CvAttachment, UUID>, CustomCvAttachmentRepository {
}
