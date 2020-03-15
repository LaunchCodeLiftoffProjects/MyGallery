package org.launchcode.mygallery.controllers;

import org.launchcode.mygallery.data.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Written by Jen Buck//
@Controller
@RequestMapping("searchArt")
public class SearchController {
    @Autowired
    private ArtworkRepository artworkRepository;

    @RequestMapping("")
    public String search() {
        return "searchArt";
    }
}
