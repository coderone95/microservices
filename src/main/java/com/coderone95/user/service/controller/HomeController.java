package com.coderone95.user.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String home(){
        return "<h6>Welcome to User Service of Pragati Test </h6>";
    }
}
