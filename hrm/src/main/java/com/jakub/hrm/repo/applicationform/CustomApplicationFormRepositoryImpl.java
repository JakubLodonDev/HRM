package com.jakub.hrm.repo.applicationform;

import com.jakub.hrm.model.ApplicationForm;
import jakarta.persistence.*;

import java.util.UUID;

public class CustomApplicationFormRepositoryImpl implements CustomApplicationFormRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveForm(ApplicationForm form) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO application_form (" +
                        "form_id, first_name, last_name, email, mobile_phone, " +
                        "street_address, city, country, zip_code, about_yourself, " +
                        "employment_status, job_id, form_attachment_id) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        query.setParameter(1, UUID.randomUUID());
        query.setParameter(2, form.getFirstName());
        query.setParameter(3, form.getLastName());
        query.setParameter(4, form.getEmail());
        query.setParameter(5, form.getMobilePhone());
        query.setParameter(6, form.getStreetAddress());
        query.setParameter(7, form.getCity());
        query.setParameter(8, form.getCountry());
        query.setParameter(9, form.getZipCode());
        query.setParameter(10, form.getAboutYourself());
        query.setParameter(11, form.getEmploymentStatus());
        query.setParameter(12, form.getJobOffer().getJobOfferId());
        query.setParameter(13, form.getFormAttachment().getFormAttachmentId());

        query.executeUpdate();
    }

    @Override
    public void updateForm(ApplicationForm form) {
        Query query = entityManager.createNativeQuery(
                "UPDATE application_form SET " +
                        "first_name = ?, " +
                        "last_name = ?, " +
                        "email = ?, " +
                        "mobile_phone = ?, " +
                        "street_address = ?, " +
                        "city = ?, " +
                        "country = ?, " +
                        "zip_code = ?, " +
                        "about_yourself = ? " +
                        "WHERE form_id = ?");

        query.setParameter(1, form.getFirstName());
        query.setParameter(2, form.getLastName());
        query.setParameter(3, form.getEmail());
        query.setParameter(4, form.getMobilePhone());
        query.setParameter(5, form.getStreetAddress());
        query.setParameter(6, form.getCity());
        query.setParameter(7, form.getCountry());
        query.setParameter(8, form.getZipCode());
        query.setParameter(9, form.getAboutYourself());
        query.setParameter(10, form.getJobOffer().getJobOfferId());

        query.executeUpdate();
    }
}
