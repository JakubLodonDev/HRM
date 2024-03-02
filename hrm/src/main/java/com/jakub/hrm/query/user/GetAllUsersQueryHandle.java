package com.jakub.hrm.query.user;

import com.jakub.hrm.model.HrUser;
import com.jakub.hrm.repo.HrUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllUsersQueryHandle {
    @Autowired
    HrUserRepo userRepo;

    public List<GetAllUsersQuery> Handle() {

        List<HrUser> userList = userRepo.findAll();

        return moveDataToQuery(userList);
    }

    private List<GetAllUsersQuery> moveDataToQuery(List<HrUser> userList) {
        List<GetAllUsersQuery> getAllUsersQueryList = new ArrayList<>();
        for (HrUser user: userList) {
            GetAllUsersQuery userQuery = new GetAllUsersQuery(user.getUserId(),
                    user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getRole().getRoleName());
            getAllUsersQueryList.add(userQuery);
        }
        return getAllUsersQueryList;
    }
}
