package com.jakub.hrm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name="cv_attachment")
public class CvAttachment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cv_attachment_id", updatable = false, nullable = false)
    private UUID cvAttachmentId;

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_type")
    private String fileType;

    @Lob
    @Column(name="data", columnDefinition="BYTEA")
    private byte[] data;

    public CvAttachment(UUID id, String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.cvAttachmentId = id;
    }

    public CvAttachment() {
    }

    public UUID getCvAttachmentId() {
        return cvAttachmentId;
    }

    public void setCvAttachmentId(UUID formAttachmentId) {
        this.cvAttachmentId = formAttachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
