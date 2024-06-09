package com.jakub.hrm.query.employeesource;

import com.jakub.hrm.query.employee.GetEmployeeByIdQuery;
import com.jakub.hrm.repo.EmployeeRepo;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import org.springframework.stereotype.Service;

public class GetEmployeeSourceByEmployeeIdQuery {

    private String sourceType;

    public GetEmployeeSourceByEmployeeIdQuery() {

    }
    public GetEmployeeSourceByEmployeeIdQuery(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
