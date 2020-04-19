package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artist;
import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.Socials;
import org.launchcode.mygallery.data.SocialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("socials")
public class SocialsController {

    @Autowired
    UserRegistrationAuthenticationController authenticationController;

    @Autowired
    private SocialsRepository socialsRepository;

    @GetMapping("create")
    public String displayCreateSocialsForm(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);
        model.addAttribute("title", "Add Social");
        model.addAttribute(new Socials());
        return "socials/create";
    }

    @PostMapping("create")
    public String processCreateSocialsForm(@ModelAttribute @Valid Socials newSocial,RedirectAttributes redirectAttributes,
                                           Errors errors, Model model, HttpServletRequest request) {

        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Social Links");
            return "socials/create";
        }

        List<Artist> artistList = generalUser.getArtists();
        newSocial.setArtist(artistList.get(0));
        socialsRepository.save(newSocial);
        redirectAttributes.addAttribute("artistId",(newSocial.getArtist()));
        return "redirect:/artist/detail";
    }


    @GetMapping("index")
    public String displayAllSocials(Model model, HttpServletRequest request) {

        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);
        model.addAttribute("socials", socialsRepository.findAll());
        return "socials/index";
    }

    @GetMapping("detail")
    public String displaySocialsDetails(@RequestParam Integer artistId, Model model, HttpServletRequest request) {

        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);
        Optional<Socials> result = socialsRepository.findById(artistId);
        Socials socials = result.get();
        model.addAttribute("title", socials.getName() + " Details");
        model.addAttribute("socials", socials);
        return "artist/detail";

    }

}
