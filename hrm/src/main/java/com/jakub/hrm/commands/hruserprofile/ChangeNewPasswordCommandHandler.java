package com.jakub.hrm.commands.hruserprofile;

import com.jakub.hrm.commands.applicationform.SubmitApplicationCommand;
import com.jakub.hrm.commands.applicationform.SubmitApplicationCommandResult;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.model.JobOffer;
import com.jakub.hrm.query.hruserprofile.GetCurrentHrUserPasswordByIdQuery;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeNewPasswordCommandHandler {
    @Autowired
    HrUserRepo hrUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CheckPasswordCommandResult Handle(GetCurrentHrUserPasswordByIdQuery newPasswordQuery, Authentication authentication) {
        HrUser user = hrUserRepo.getByIdentification_Login(authentication.getName());

        var result = Validate(newPasswordQuery, user);
        if (result != null) {
            return result;
        }
        user.changePassword(new BCryptPasswordEncoder().encode(newPasswordQuery.getPwd()));
        hrUserRepo.save(user);
        return new CheckPasswordCommandResult(true);
    }

    private CheckPasswordCommandResult Validate(GetCurrentHrUserPasswordByIdQuery newPasswordQuery, HrUser user) {

        if (!passwordEncoder.matches(newPasswordQuery.getCurrentPassword(), user.getIdentification().getPwd())) {
            return new CheckPasswordCommandResult(false, "Current password is incorrect");
        }
        return null;
    }
}
