package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Written by Jen Buck
@Controller
@RequestMapping("userRegistration")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @GetMapping("create") //This should create a new User
    public String displayUserRegistrationForm(Model model) {
        model.addAttribute("title", "User Registration");
        model.addAttribute(new User()); //Get whatever the name for User class to put here
        return "userRegistration/create";
    }

    @PostMapping("create")//This should add the user to the userRegistrationRepository
    public String processUserRegistrationForm(@ModelAttribute @Valid User newUser, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "User Registration");
            return "userRegistration/create";
        }

        userRegistrationRepository.save(newUser);
        return "redirect";
    }
}
