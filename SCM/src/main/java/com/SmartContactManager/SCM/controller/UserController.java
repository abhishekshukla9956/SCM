package com.SmartContactManager.SCM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
    //user dashboard
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashboard() {
        System.out.println("user dashboard");
        return "user/dashboard";
    }
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userProfile() {
        System.out.println("user profile");
        return "user/profile";
    }

    //user add contact page
}
