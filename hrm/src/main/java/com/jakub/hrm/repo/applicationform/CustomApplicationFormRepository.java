package com.jakub.hrm.repo.applicationform;

import com.jakub.hrm.model.ApplicationForm;

public interface CustomApplicationFormRepository {
    void saveForm(ApplicationForm form);
    void updateForm(ApplicationForm form);
}
