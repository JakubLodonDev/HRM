package com.jakub.hrm.query.hruserprofile;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetCurrentHrUserPasswordByIdQueryHandler {

    HrUserRepo hrUserRepo;

    @Autowired
    public GetCurrentHrUserPasswordByIdQueryHandler(HrUserRepo hrUserRepo) {
        this.hrUserRepo = hrUserRepo;
    }

    public GetCurrentHrUserPasswordByIdQuery Handle(String userId) {
        Optional<HrUser> dbHrUser = hrUserRepo.findById(UUID.fromString(userId));
        if(dbHrUser.isPresent()){
            HrUser hrUser = dbHrUser.get();
            return new GetCurrentHrUserPasswordByIdQuery(hrUser.getIdentification().getPwd());
        }
        return new GetCurrentHrUserPasswordByIdQuery();
    }
}
