package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.launchcode.mygallery.models.dto.UserRegistrationFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

//Written by Jen Buck
@Controller
@RequestMapping("register")
public class UserRegistrationAuthenticationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    private static final String userSessionKey = "user"; //If this doesn't work, try "generalUser"

    public GeneralUser getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<GeneralUser> generalUser = userRegistrationRepository.findById(userId);

        if (generalUser.isEmpty()) {
            return null;
        }

        return generalUser.get();
    }

    private static void setUserInSession(HttpSession session, GeneralUser generalUser) {
        session.setAttribute(userSessionKey, generalUser.getId());
    }

    @GetMapping() //This should create a new User
    public String displayUserRegistrationForm(Model model) {
        model.addAttribute(new UserRegistrationFormDTO());
        model.addAttribute("title", "User Registration");
        return "register";
    }

    @PostMapping()//This should add the user to the userRegistrationRepository
    public String processUserRegistrationForm(@ModelAttribute @Valid GeneralUser newGeneralUser, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "User Registration");
            return "register";
        }

        userRegistrationRepository.save(newGeneralUser);
        return "redirect";
    }
}
