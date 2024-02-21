package com.jakub.hrm.query.joboffer;

public class JobOfferExistsQueryResult {

    public boolean succeeded;
    public String errorMessage;

    public JobOfferExistsQueryResult(boolean succeeded) {
        this.succeeded = succeeded;
    }
    public JobOfferExistsQueryResult(boolean succeeded, String errorMessage) {
        this.succeeded = succeeded;
        this.errorMessage = errorMessage;
    }
}
