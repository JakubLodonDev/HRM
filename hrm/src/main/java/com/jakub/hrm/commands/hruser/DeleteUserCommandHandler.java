package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteUserCommandHandler {

    @Autowired
    HrUserRepo hrUserRepo;

    public void Handle(String userId) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(UUID.fromString(userId));
        if (dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            hrUserRepo.delete(hrUser);
        }
    }
}
