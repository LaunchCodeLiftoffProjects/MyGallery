package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

//Written by Jen Buck
@Controller
@RequestMapping("userRegistration")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @GetMapping() //This should create a new User
    public String displayUserRegistrationForm(Model model) {
        model.addAttribute("title", "User Registration");
        model.addAttribute(new GeneralUser());
        return "userRegistration/create";
    }

    @PostMapping()//This should add the user to the userRegistrationRepository
    public String processUserRegistrationForm(@ModelAttribute @Valid GeneralUser newGeneralUser, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "User Registration");
            return "userRegistration/create";
        }

        userRegistrationRepository.save(newGeneralUser);
        return "redirect";
    }
}
