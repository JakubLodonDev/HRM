package com.jakub.hrm.repo;

import com.jakub.hrm.model.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepo extends CrudRepository<ApplicationForm, String> {
}
