package com.jakub.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping("/career/application")
    public String displayApplicationPage(){
        return "application.html";
    }
}
