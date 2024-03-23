package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.model.HrIdentification;
import com.jakub.hrm.model.HrRole;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrIdentificationRepo;
import com.jakub.hrm.repo.HrRoleRepo;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewHrCommandHandler {

    HrUserRepo hrUserRepo;
    HrIdentificationRepo hrIdentificationRepo;
    HrRoleRepo hrRoleRepo;

    PasswordEncoder passwordEncoder;

    @Autowired
    public NewHrCommandHandler(HrUserRepo hrUserRepo, HrIdentificationRepo hrIdentificationRepo,
                               HrRoleRepo hrRoleRepo, PasswordEncoder passwordEncoder) {
        this.hrUserRepo = hrUserRepo;
        this.hrIdentificationRepo = hrIdentificationRepo;
        this.hrRoleRepo = hrRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void Handle(NewHrUserCommand command) {

        HrIdentification identification = new HrIdentification(command.getEmail(), passwordEncoder.encode(command.getIdentification().getPwd()));

        HrRole role = hrRoleRepo.findByRoleName(command.getRole().getRoleName());

        HrUser user = new HrUser(command.getFirstName(), command.getLastName(), command.getEmail(), true,
                identification, role);

        hrUserRepo.save(user);
    }
}
