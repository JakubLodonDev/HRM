package com.jakub.hrm.repo.cvattachment;

import com.jakub.hrm.model.CvAttachment;
import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;

public class CustomCvAttachmentRepositoryImpl implements CustomCvAttachmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCvAttachment(CvAttachment file) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO cv_attachment " +
                        "(cv_attachment_id, file_name, file_type, data)" +
                        " VALUES (?, ?, ?, ?)");
        query.setParameter(1, file.getCvAttachmentId());
        query.setParameter(2, file.getFileName());
        query.setParameter(3, file.getFileType());
        query.setParameter(4, file.getData());

        query.executeUpdate();
    }

    public CvAttachment getCvAttachment(UUID id) throws SQLException {
        Query query = entityManager.createNativeQuery(
                "SELECT cv_attachment_id, file_name, file_type, data " +
                        "FROM cv_attachment " +
                        "WHERE cv_attachment_id = :id", Tuple.class);
        query.setParameter("id", id);
        Tuple result;

        result = (Tuple) query.getSingleResult();

        UUID cvAttachmentId = result.get("cv_attachment_id", UUID.class);
        String fileName = result.get("file_name", String.class);
        String fileType = result.get("file_type", String.class);
        byte[] data;

        Object dataObject = result.get("data");

        if (dataObject instanceof Blob) {
            Blob blob = (Blob) dataObject;
            data = blob.getBytes(1, (int) blob.length());
        } else if (dataObject instanceof byte[]) {
            data = (byte[]) dataObject;
        } else {
            throw new SQLException("Unexpected data type for 'data' column");
        }

        return new CvAttachment(cvAttachmentId, fileName, fileType, data);
    }

}
