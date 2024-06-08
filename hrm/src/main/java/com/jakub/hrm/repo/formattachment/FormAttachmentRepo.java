package com.jakub.hrm.repo.formattachment;

import com.jakub.hrm.model.FormAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormAttachmentRepo extends JpaRepository<FormAttachment, UUID>, CustomFormAttachmentRepository {
}
