package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.Artist;
import org.launchcode.mygallery.ArtistData;
import org.launchcode.mygallery.data.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

//Written by Jen Buck//
@Controller
@RequestMapping("searchArtist")
public class SearchArtistController {

    static HashMap<String, String> columnChoices = new HashMap<>();
    public SearchArtistController() {
        columnChoices.put("all", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("social links", "Social Links");
    }

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("columns", columnChoices);
        return "searchArtist";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Artist> artists;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            artists = artistRepository.findAll();
        }else {
            artists = ArtistData.findByColumnAndValue(searchType, searchTerm, artistRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Artists with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("artists", artists);

        return "searchArtist";
    }
}
