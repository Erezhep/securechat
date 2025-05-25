package com.chat.securechat.controller;


import com.chat.securechat.entity.User;
import com.chat.securechat.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ===== Показ форм =====
    @GetMapping("/login")
    public String loginPage(){
        return "login"; // возвращает login.html из templates
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userForm", new User());
        return "register"; // возвращает register.html из templates
    }

    // ===== Обработка форм =====
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("userForm") User user, Model model){
        boolean success = userService.registerUser(user.getUsername(), user.getPassword());
        if (!success){
            model.addAttribute("error", "Имя пользователя уже занято");
            return "register";
        }
        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String handleLogin(@RequestParam String username,
//                              @RequestParam String password,
//                              HttpSession session,
//                              Model model){
//        Optional<User> userOpt = userService.authenticate(username, password);
//        if (userOpt.isPresent()){
//            session.setAttribute("user", userOpt.get());
//            return "redirect:/chat";
//        } else {
//            model.addAttribute("error", "Неверный логин или пароль");
//            return "login";
//        }
//    }

    // ===== Чат и выход =====
    @GetMapping("/chat")
    public String chatPage(){
        return "chat";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}
