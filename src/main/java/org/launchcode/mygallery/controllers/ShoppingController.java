package org.launchcode.mygallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShoppingController {

    @GetMapping("shopping")
    public String comingSoon(){
        return "shopping";
    }

}
