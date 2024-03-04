package com.jakub.hrm.repo;

import com.jakub.hrm.model.HrRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HrRoleRepo extends JpaRepository<HrRole, UUID> {

    HrRole findByRoleName(String role);
}
