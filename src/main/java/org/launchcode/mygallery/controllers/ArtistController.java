package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artist;
import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    UserRegistrationAuthenticationController authenticationController;

    @GetMapping("create")
    public String displayCreateArtistForm (Model model, HttpServletRequest request){
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        model.addAttribute("title", "Create Artist");
        model.addAttribute(new Artist());

        return "artist/create";
    }

    @PostMapping("create")
     public String processCreateArtistForm(@ModelAttribute @Valid Artist newArtist,
                                          Errors errors, Model model, HttpServletRequest request){
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        if (errors.hasErrors()){
            model.addAttribute("title","Create Artist");
            return "artist/create;";
        }
        newArtist.setArtistUserId(generalUser.getId());
        artistRepository.save(newArtist);


        return "redirect:index";
    }

    @GetMapping("index")
    public String displayAllArtists(Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        model.addAttribute("title", "Artists");
        model.addAttribute("artists", artistRepository.findAll());
        return "artist/index";
    }

    @GetMapping("detail")
    public String displayArtistDetails(@RequestParam Integer artistId, Model model, HttpServletRequest request) {
        GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("user", generalUser);

        Optional<Artist> result = artistRepository.findById(artistId);

        Artist artist = result.get();
        model.addAttribute("title", artist.getArtistName());
        model.addAttribute("artist", artist);

        return "artist/detail";
    }
}