package com.jakub.hrm.query.role;

import com.jakub.hrm.model.HrRole;
import com.jakub.hrm.query.HrRoleQuery;
import com.jakub.hrm.repo.HrRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GetAllRolesHandle {

    @Autowired
    HrRoleRepo hrRoleRepo;

    public List<String> Handle() {
        List<HrRole> roleList = hrRoleRepo.findAll();
        List<String> roleQueryList = new ArrayList<>();
        for (HrRole role : roleList) {
            roleQueryList.add(role.getRoleName());
        }
        return roleQueryList;
    }
}
