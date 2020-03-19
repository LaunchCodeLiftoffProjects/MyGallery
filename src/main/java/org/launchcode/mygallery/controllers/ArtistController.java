package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artist;
import org.launchcode.mygallery.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("create")
    public String displayCreateArtistForm (Model model){
        model.addAttribute("title", "Create Artist");
        model.addAttribute(new Artist());
        return "artist/create";
    }

    @PostMapping("create")
     public String processCreateArtistForm(@ModelAttribute @Valid Artist newArtist,
                                          Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title","Create Artist");
            return "artist/create;";
        }
        artistRepository.save(newArtist);
        return "redirect:";
    }

    @GetMapping("index")
    public String displayAllArtists(Model model) {

        model.addAttribute("title", "Artists");
        model.addAttribute("artists", artistRepository.findAll());
        return "artist/index";
    }
}