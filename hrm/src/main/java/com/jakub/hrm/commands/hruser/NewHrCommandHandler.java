package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.model.HrIdentification;
import com.jakub.hrm.model.HrRole;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrIdentificationRepo;
import com.jakub.hrm.repo.HrRoleRepo;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewHrCommandHandler {

    HrUserRepo hrUserRepo;
    HrIdentificationRepo hrIdentificationRepo;
    HrRoleRepo hrRoleRepo;

    @Autowired
    public NewHrCommandHandler(HrUserRepo hrUserRepo, HrIdentificationRepo hrIdentificationRepo,
                               HrRoleRepo hrRoleRepo) {
        this.hrUserRepo = hrUserRepo;
        this.hrIdentificationRepo = hrIdentificationRepo;
        this.hrRoleRepo = hrRoleRepo;
    }

    public void Handle(NewHrUserCommand command) {
        HrIdentification identification = new HrIdentification(command.getEmail(), command.getIdentification().getPwd());
        //hrIdentificationRepo.save(identification);

        HrRole role = hrRoleRepo.findByRoleName(command.getRole().getRoleName());

        HrUser user = new HrUser(command.getFirstName(), command.getLastName(), command.getEmail(),
                identification, role);

        hrUserRepo.save(user);
    }
}
