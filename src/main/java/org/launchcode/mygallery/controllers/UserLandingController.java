package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLandingController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @GetMapping("landing")
    public String userPage(){
        return "landing";
    }

}
