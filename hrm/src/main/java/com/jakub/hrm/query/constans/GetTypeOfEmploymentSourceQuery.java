package com.jakub.hrm.query.constans;

import com.jakub.hrm.constans.TypeOfEmploymentSource;

import java.util.Arrays;
import java.util.List;

public class GetTypeOfEmploymentSourceQuery {

    public static List<String> Handle() {
        return Arrays.asList(
                TypeOfEmploymentSource.APPLICATION,
                TypeOfEmploymentSource.REFERENCE,
                TypeOfEmploymentSource.OTHER
        );
    }
}
