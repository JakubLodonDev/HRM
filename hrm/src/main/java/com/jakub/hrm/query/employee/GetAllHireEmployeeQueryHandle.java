package com.jakub.hrm.query.employee;

import com.jakub.hrm.constans.EmploymentStatus;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllHireEmployeeQueryHandle {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<GetAllEmployeeQuery> Handle() {

        List<Employee> employeeList = employeeRepo.findAllByEmploymentStatus(EmploymentStatus.HIRE);

        return moveDataToQuery(employeeList);
    }

    private List<GetAllEmployeeQuery> moveDataToQuery(List<Employee> employeeList) {
        List<GetAllEmployeeQuery> getAllEmployeeQueryList = new ArrayList<>();
        for (Employee emplo: employeeList) {
            GetAllEmployeeQuery employeeQuery = new GetAllEmployeeQuery(emplo.getEmployeeId(),
                    emplo.getFirstName(), emplo.getLastName(), emplo.getEmail(), emplo.getPosition_name());
            getAllEmployeeQueryList.add(employeeQuery);
        }
        return getAllEmployeeQueryList;
    }
}
