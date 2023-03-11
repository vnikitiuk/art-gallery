package org.viktoriianikitiuk.artoffreedom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;

@Controller
public class GalleryController {

    @Autowired
    private PaintingRepository paintingRepository;

    @GetMapping("/gallery")
    String gallery(Model model, Authentication authentication) {
        model.addAttribute("paintings", paintingRepository.findAll());
        model.addAttribute("loggedIn", authentication != null);

        return "gallery";
    }


}
