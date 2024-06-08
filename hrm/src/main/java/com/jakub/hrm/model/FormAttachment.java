package com.jakub.hrm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name="form_attachment")
public class FormAttachment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "form_attachment_id", updatable = false, nullable = false)
    private UUID formAttachmentId;

    @Column(name="file_name")
    private String fileName;

    @Column(name="file_type")
    private String fileType;

    @Lob
    @Column(name="data", columnDefinition="BYTEA")
    private byte[] data;

    public FormAttachment(UUID id,String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.formAttachmentId = id;
    }

    public FormAttachment() {

    }

    public UUID getFormAttachmentId() {
        return formAttachmentId;
    }

    public void setFormAttachmentId(UUID formAttachmentId) {
        this.formAttachmentId = formAttachmentId;
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
