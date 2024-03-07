package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.commands.hruser.NewHrIdentificationCommand;
import com.jakub.hrm.model.HrIdentification;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetNewPasswordCommandHandler {

    @Autowired
    HrUserRepo hrUserRepo;

    public void Handle(NewHrIdentificationCommand newPasswordRequest, Authentication authentication) {
        HrUser user = hrUserRepo.getByIdentification_Login(authentication.getName());
        HrIdentification identification = user.getIdentification();
        identification.setPwd(new BCryptPasswordEncoder().encode(newPasswordRequest.getPwd()));
        user.setPasswordChangeRequired(false);
        hrUserRepo.save(user);
    }
}
