package com.jakub.hrm.repo.formattachment;

import com.jakub.hrm.model.FormAttachment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;

public class CustomFormAttachmentRepositoryImpl implements CustomFormAttachmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveFormAttachment(FormAttachment file) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO form_attachment " +
                        "(form_attachment_id, file_name, file_type, data)" +
                        " VALUES (?, ?, ?, ?)");
        query.setParameter(1, file.getFormAttachmentId());
        query.setParameter(2, file.getFileName());
        query.setParameter(3, file.getFileType());
        query.setParameter(4, file.getData());

        query.executeUpdate();
    }

    /*
    public ApplicationForm getApplicationFormWithAttachment(UUID id) {
        TypedQuery<ApplicationForm> query = entityManager.createQuery(
                "SELECT a " +
                        "FROM ApplicationForm a " +
                        "LEFT JOIN FETCH a.formAttachment " +
                        "WHERE a.applicationFormId = :id", ApplicationForm.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
     */
    /*
    public FormAttachment getFormAttachment(UUID id) {
        Query query = entityManager.createNativeQuery(
                "SELECT a FROM form_attachment a  WHERE a.form_attachment_id = :id");
        query.setParameter("id", id);
        var result = (Object[]) query.getSingleResult();

        var fileName =  result[1].toString();
        var file_type =  result[2].toString();
        var data = (byte[]) result[3];

        return new FormAttachment(id,fileName,file_type,data);
    }

     */

    public FormAttachment getFormAttachment(UUID id) throws SQLException {
        Query query = entityManager.createNativeQuery(
                "SELECT a.form_attachment_id, a.file_name, a.file_type, a.data " +
                        "FROM form_attachment a " +
                        "WHERE a.form_attachment_id = :id", Tuple.class);
        query.setParameter("id", id);
        Tuple result = (Tuple) query.getSingleResult();

        UUID formAttachmentId = result.get("form_attachment_id", UUID.class);
        String fileName = result.get("file_name", String.class);
        String fileType = result.get("file_type", String.class);
        byte[] data;

        Object dataObject = result.get("data");

        if (dataObject instanceof Blob) {
            Blob blob = (Blob) dataObject;
            data = blob.getBytes(1, (int) blob.length());
        } else {
            data = (byte[]) dataObject;
        }

        return new FormAttachment(formAttachmentId, fileName, fileType, data);
    }
}
