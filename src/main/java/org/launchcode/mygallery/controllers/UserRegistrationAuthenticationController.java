package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.launchcode.mygallery.models.dto.LoginFormDTO;
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
@RequestMapping()
public class UserRegistrationAuthenticationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private UserRegistrationAuthenticationController authenticationController;

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

    @GetMapping("/register") //This should create a new User
    public String displayUserRegistrationForm(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        model.addAttribute(new UserRegistrationFormDTO());
        model.addAttribute("title", "User Registration");
        return "register";
    }

    @PostMapping("/register")//This should add the user to the userRegistrationRepository
    public String processUserRegistrationForm(@ModelAttribute @Valid UserRegistrationFormDTO userRegistrationFormDTO, Errors errors, HttpServletRequest request, Model model) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

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

        if (newGeneralUser.getRole().equals("explorer")) {
            return "redirect:/landing";
        } else if (newGeneralUser.getRole().equals("artist")) {
            return "redirect:/artist/create";
        } else {
            return "register";
        }
    }

    //This section written by Austin Yates
    @GetMapping("/login")
    public String displayLoginForm(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        GeneralUser theUser = userRegistrationRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/landing";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
