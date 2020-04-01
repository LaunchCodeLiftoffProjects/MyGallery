package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Socials;
import org.launchcode.mygallery.data.SocialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("artist/detail")
public class SocialsController {

    @Autowired
    private SocialsRepository socialsRepository;

    @RequestMapping("")
    public String displayArtist(Model model) {
        model.addAttribute("artist", socialsRepository.findAll());
        return "artist/detail";
    }

    @GetMapping("artist/create")
    public String displayAddSocialsForm(Model model){
        model.addAttribute(new Socials());
        return "artist/create";
    }

    @PostMapping("artist/create")
    public String processAddSocialForm(@ModelAttribute @Valid Socials newSocial, Errors errors,
                                      Model model) {
        if (errors.hasErrors()) {
            return "artist/create";
        }

        socialsRepository.save(newSocial);
        return "redirect:";
    }

    @GetMapping("artist/detail/{artistId}")
    public String displayViewSocials(Model model, @PathVariable int artistId) {

        Optional optSocial = socialsRepository.findById(artistId);
        if (optSocial.isPresent()) {
            Socials socials = (Socials) optSocial.get();
            model.addAttribute("socials", socials);
            return "artist/detail";
        } else {
            return "redirect:../";
        }
    }


}
