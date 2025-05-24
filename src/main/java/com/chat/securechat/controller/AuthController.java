package com.chat.securechat.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(){
        return "login"; // возвращает login.html из templates
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register"; // возвращает register.html из templates
    }

}
