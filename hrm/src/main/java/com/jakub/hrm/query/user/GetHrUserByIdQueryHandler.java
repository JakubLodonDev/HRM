package com.jakub.hrm.query.user;

import com.jakub.hrm.model.HrRole;
import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetHrUserByIdQueryHandler {

    @Autowired
    HrUserRepo hrUserRepo;

    public GetHrUserByIdQuery Handle(String userId) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(UUID.fromString(userId));
        if(dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            return new GetHrUserByIdQuery(hrUser.getFirstName(), hrUser.getLastName(),
                    hrUser.getEmail(), hrUser.getRole().getRoleName(),
                    hrUser.isPasswordChangeRequired());
        }
        return new GetHrUserByIdQuery();
    }
}
