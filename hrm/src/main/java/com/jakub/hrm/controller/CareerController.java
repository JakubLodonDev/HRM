package com.jakub.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CareerController {

    @RequestMapping("/career")
    public String dislayCareerPage(){
        return "career.html";
    }
}
