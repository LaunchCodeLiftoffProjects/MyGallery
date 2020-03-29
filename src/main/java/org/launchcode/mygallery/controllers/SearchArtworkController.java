package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artwork;
import org.launchcode.mygallery.ArtworkData;
import org.launchcode.mygallery.data.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


//Written by Jen Buck//
@Controller
@RequestMapping("searchArt")
public class SearchArtworkController {

    static HashMap<String, String> columnChoices = new HashMap<>();
    public SearchArtworkController() {
        columnChoices.put("all", "All");
        columnChoices.put("title", "Title");
        columnChoices.put("description", "Description");
        columnChoices.put("medium", "Medium");
        columnChoices.put("genre", "Genre");
        columnChoices.put("size", "Size");
    }

    @Autowired
    private ArtworkRepository artworkRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "searchArt";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Artwork> artworks;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            artworks = artworkRepository.findAll();
        }else {
            artworks = ArtworkData.findByColumnAndValue(searchType, searchTerm, artworkRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Artwork with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("artworks", artworks);

        return "searchArt";
    }
}
