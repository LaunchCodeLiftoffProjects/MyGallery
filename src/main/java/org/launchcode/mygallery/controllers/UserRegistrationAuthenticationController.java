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

import javax.servlet.http.HttpServletRequest;
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
    public String processUserRegistrationForm(@ModelAttribute @Valid UserRegistrationFormDTO userRegistrationFormDTO, Errors errors, HttpServletRequest request, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "User Registration");
            return "register";
        }

        GeneralUser existingUser = userRegistrationRepository.findByUsername(userRegistrationFormDTO.getUsername());

        if(existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "Someone is already using that name. Please try again. ");
            model.addAttribute("title", "User Registration");
            return "register";
        }

        String password = userRegistrationFormDTO.getPassword();
        String verifyPassword = userRegistrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match.");
            model.addAttribute("title", "User Registration");
            return "register";
        }

        GeneralUser newGeneralUser = new GeneralUser(userRegistrationFormDTO.getUsername(),userRegistrationFormDTO.getPassword(), userRegistrationFormDTO.getRole());
        userRegistrationRepository.save(newGeneralUser);
        setUserInSession(request.getSession(),newGeneralUser);

        return "redirect";
    }
}
