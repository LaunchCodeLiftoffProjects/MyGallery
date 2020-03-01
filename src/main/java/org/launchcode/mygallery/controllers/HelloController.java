package org.launchcode.mygallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //Handles request at /hello
    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

}
