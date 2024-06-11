package com.jakub.hrm.repo.employee;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmploymentSource;

public interface CustomEmployeeRepository {
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);

    void saveEmployeeSource(EmploymentSource employmentSource);
}
