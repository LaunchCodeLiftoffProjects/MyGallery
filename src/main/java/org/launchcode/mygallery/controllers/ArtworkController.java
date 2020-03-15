package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("artwork")
public class ArtworkController {



        @Autowired
        private ArtworkRepository artworkRepository;

        @GetMapping("create")
        public String displayCreateArtworkForm(Model model) {
            model.addAttribute("title", "Add Artwork");
            model.addAttribute(new Artwork());
            return "artwork/create";
        }

        @PostMapping("create")
        public String processCreateArtworkForm(@ModelAttribute @Valid Artwork newArtwork,
                                             Errors errors, Model model) {
            if(errors.hasErrors()) {
                model.addAttribute("title", "Add Artwork");
                return "artwork/create";
            }

            artworkRepository.save(newArtwork);
            return "redirect:index";
        }

        @GetMapping("index")
        public String displayAllArtwork(Model model) {

            model.addAttribute("title", "Artwork");
            model.addAttribute("artworks", artworkRepository.findAll());
            return "artwork/index";
        }

        @GetMapping("detail")
        public String displayArtworkDetails(@RequestParam Integer artworkId, Model model) {

            Optional<Artwork> result = artworkRepository.findById(artworkId);

                Artwork artwork = result.get();
                model.addAttribute("title", artwork.getTitle() + " Details");
                model.addAttribute("artwork", artwork);
                return "artwork/detail";
        }

    }
