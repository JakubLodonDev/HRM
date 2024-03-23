package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordHrUserCommandHandler {

    @Autowired
    HrUserRepo hrUserRepo;
    @Value("${default.reset.password.for.user}")
    private String defaultPassword;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void Handle(String userId) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(UUID.fromString(userId));
        if(dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            hrUser.resetPassword(passwordEncoder.encode(defaultPassword));
            hrUserRepo.save(hrUser);
        }
    }
}
