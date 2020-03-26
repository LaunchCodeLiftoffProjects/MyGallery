package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.launchcode.mygallery.storage.RemoteStorageService;
import org.launchcode.mygallery.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
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

        @GetMapping("create")
        public String displayCreateArtworkForm(Model model) {
            model.addAttribute("title", "Add Artwork");
            model.addAttribute(new Artwork());
            return "artwork/create";
        }

       @PostMapping("create")
        public String processCreateArtworkForm(@ModelAttribute @Valid Artwork newArtwork,
                                             Errors errors, Model model, RedirectAttributes redirectAttributes) {
            if(errors.hasErrors()) {
                model.addAttribute("title", "Add Artwork");
                return "artwork/create";
            }

           artworkRepository.save(newArtwork);
           redirectAttributes.addAttribute("artworkId",(newArtwork.getId()));
            return "redirect:upload";
        }


        //https://px8mbx3go1.execute-api.us-east-2.amazonaws.com/default/myPhotoUploadFunction?filename=shivanytest.jpeg
        @PostMapping("upload")
        public String handleArtworkUpload(@RequestParam("file") MultipartFile file, @RequestParam Integer artworkId, RedirectAttributes redirectAttributes) {

            File convFile = storageService.convert(file);
            String encodstring = RemoteStorageService.encodeFileToBase64Binary(convFile);

            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            Optional<Artwork> result = artworkRepository.findById(artworkId);
            Artwork artwork = result.get();
            artwork.setArtLink(file.getOriginalFilename());
            return "redirect:index";
        }

        @GetMapping("upload")
        public String displayUploadArtworkForm(Model model, @RequestParam Integer artworkId) {
          
            model.addAttribute("title", "Add Artwork Image");
            model.addAttribute("artworkId", artworkId);
            return "artwork/upload";
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
