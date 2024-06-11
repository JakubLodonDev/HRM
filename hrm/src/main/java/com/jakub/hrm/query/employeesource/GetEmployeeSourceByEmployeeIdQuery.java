package com.jakub.hrm.query.employeesource;

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
