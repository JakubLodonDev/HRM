package com.jakub.hrm.repo.employee;

import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmploymentSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.UUID;

public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmployee(Employee employee) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO employee (" +
                        "employee_id, first_name, last_name, email, mobile_phone, " +
                        "employment_status, position_name, employee_address_id, " +
                        "cv_attachment_id) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        query.setParameter(1, employee.getEmployeeId());
        query.setParameter(2, employee.getFirstName());
        query.setParameter(3, employee.getLastName());
        query.setParameter(4, employee.getEmail());
        query.setParameter(5, employee.getMobilePhone());
        query.setParameter(6, employee.getEmploymentStatus());
        query.setParameter(7, employee.getPosition_name());
        query.setParameter(8, employee.getEmployeeAddress().getEmployeeAddressId());
        query.setParameter(9, employee.getCvAttachment().getCvAttachmentId());

        query.executeUpdate();
    }

    @Override
    public void updateEmployee(Employee employee) {
        Query query = entityManager.createNativeQuery(
                "UPDATE employee SET " +
                        "first_name = ?, " +
                        "last_name = ?, " +
                        "email = ?, " +
                        "mobile_phone = ?, " +
                        "position_name = ?, " +
                        "employee_address_id = ? " +
                        "WHERE employee_id = ?");

        query.setParameter(1, employee.getFirstName());
        query.setParameter(2, employee.getLastName());
        query.setParameter(3, employee.getEmail());
        query.setParameter(4, employee.getMobilePhone());
        query.setParameter(5, employee.getPosition_name());
        query.setParameter(6, employee.getEmployeeAddress().getEmployeeAddressId());
        query.setParameter(7, employee.getEmployeeId());

        query.executeUpdate();
    }

    @Override
    public void saveEmployeeSource(EmploymentSource employmentSource) {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO employment_source (" +
                        "employment_source_id, source_type, form_id, employee_id" +
                        "VALUES ( ?, ?, ?, ?)");
        query.setParameter(1, employmentSource.getEmployeeSourceId());
        query.setParameter(2, employmentSource.getSourceType());
        query.setParameter(3, employmentSource.getApplicationForm().getApplicationFormId());
        query.setParameter(4, employmentSource.getEmployee().getEmployeeId());

        query.executeUpdate();
    }
}
