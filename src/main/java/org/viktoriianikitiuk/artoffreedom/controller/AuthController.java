package org.viktoriianikitiuk.artoffreedom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;
import org.viktoriianikitiuk.artoffreedom.dao.RoleRepository;
import org.viktoriianikitiuk.artoffreedom.dao.UserRepository;
import org.viktoriianikitiuk.artoffreedom.model.User;
import org.viktoriianikitiuk.artoffreedom.service.UserServiceImpl;

@Controller
public class AuthController {
    private UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("user") User user){
        userService.create(user);
        return "redirect:/login";
    }
}
