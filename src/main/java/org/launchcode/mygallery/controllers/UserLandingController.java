package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.launchcode.mygallery.data.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserLandingController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    UserRegistrationAuthenticationController authenticationController;

    @GetMapping("landing")
    public String userPage(Model model, HttpServletRequest request){
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);
        model.addAttribute("artworks", artworkRepository.findAll());

        Iterable<Artwork> artworks = artworkRepository.findAll();
        Artwork firstArtwork = artworks.iterator().next();
        model.addAttribute("firstArtwork",firstArtwork);
        return "landing";
    }

}
