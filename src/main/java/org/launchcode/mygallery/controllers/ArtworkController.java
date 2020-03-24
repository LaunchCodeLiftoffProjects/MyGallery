package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.GeneralUser;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.launchcode.mygallery.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("artwork")
public class ArtworkController {

        private final StorageService storageService;

        @Autowired
        public ArtworkController(StorageService storageService) {
        this.storageService = storageService;
    }

        @Autowired
        private ArtworkRepository artworkRepository;

        @Autowired
        UserRegistrationAuthenticationController authenticationController;

        @GetMapping("create")
        public String displayCreateArtworkForm(Model model, HttpServletRequest request) {
            GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("user", generalUser);

            model.addAttribute("title", "Add Artwork");
            model.addAttribute(new Artwork());
            return "artwork/create";
        }

       @PostMapping("create")
        public String processCreateArtworkForm(@ModelAttribute @Valid Artwork newArtwork,
                                             Errors errors, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
           GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
           model.addAttribute("user", generalUser);

            if(errors.hasErrors()) {
                model.addAttribute("title", "Add Artwork");
                return "artwork/create";
            }

           artworkRepository.save(newArtwork);
           redirectAttributes.addAttribute("artworkId",(newArtwork.getId()));
            return "redirect:upload";
        }

        @PostMapping("upload")
        public String handleArtworkUpload(@RequestParam("file") MultipartFile file, @RequestParam Integer artworkId, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
            GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("user", generalUser);

            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            Optional<Artwork> result = artworkRepository.findById(artworkId);
            Artwork artwork = result.get();
            artwork.setArtLink(file.getOriginalFilename());
            return "redirect:index";
        }

        @GetMapping("upload")
        public String displayUploadArtworkForm(Model model, @RequestParam Integer artworkId, HttpServletRequest request) {
            GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("user", generalUser);

            model.addAttribute("title", "Add Artwork Image");
            model.addAttribute("artworkId", artworkId);
            return "artwork/upload";
        }

        @GetMapping("index")
        public String displayAllArtwork(Model model, HttpServletRequest request) {
            GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("user", generalUser);

            model.addAttribute("title", "Artwork");
            model.addAttribute("artworks", artworkRepository.findAll());
            return "artwork/index";
        }

        @GetMapping("detail")
        public String displayArtworkDetails(@RequestParam Integer artworkId, Model model, HttpServletRequest request) {
            GeneralUser generalUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("user", generalUser);

            Optional<Artwork> result = artworkRepository.findById(artworkId);

                Artwork artwork = result.get();
                model.addAttribute("title", artwork.getTitle() + " Details");
                model.addAttribute("artwork", artwork);
                return "artwork/detail";
        }

    }
