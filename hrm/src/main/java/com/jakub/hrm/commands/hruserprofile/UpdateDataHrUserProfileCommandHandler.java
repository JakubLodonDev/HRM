package com.jakub.hrm.commands.hruserprofile;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDataHrUserProfileCommandHandler {
    @Autowired
    HrUserRepo hrUserRepo;

    public void Handle(UpdateDataHrUserProfileCommand command) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(command.getUserId());
        if(dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            hrUser.updateProfile(command.getFirstName(), command.getLastName(),
                    command.getEmail());
            hrUserRepo.save(hrUser);
        }
    }
}
