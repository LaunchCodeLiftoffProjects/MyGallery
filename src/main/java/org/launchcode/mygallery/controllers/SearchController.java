package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

//Written by Jen Buck//
@Controller
@RequestMapping("searchArt")
public class SearchController {
    @Autowired
    private ArtworkRepository artworkRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("artworks", artworkRepository.findAll());
        return "searchArt";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm){
//put code here after making the ArtworkData class
        return "searchArt";
    }
}
