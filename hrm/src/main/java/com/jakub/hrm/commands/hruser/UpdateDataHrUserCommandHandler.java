package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.model.HrRole;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrRoleRepo;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDataHrUserCommandHandler {

    @Autowired
    HrUserRepo hrUserRepo;

    @Autowired
    HrRoleRepo hrRoleRepo;

    public void Handle(UpdateDataHrUserCommand command) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(command.getUserId());
        if(dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            HrRole hrRole = hrRoleRepo.findByRoleName(command.getRoleName());
            hrUser.updateData(command.getFirstName(), command.getLastName(), command.getEmail(), hrRole);
            hrUserRepo.save(hrUser);
        }
    }
}
