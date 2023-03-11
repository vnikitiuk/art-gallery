package org.viktoriianikitiuk.artoffreedom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;
import org.viktoriianikitiuk.artoffreedom.model.Painting;
import org.viktoriianikitiuk.artoffreedom.config.UserPrincipal;

import java.util.Optional;

@Controller
public class BuyController {
    @Autowired
    private PaintingRepository paintingRepository;

    @GetMapping("/buy/{id}")
    String editPainting(Model model, @PathVariable("id") Long id, Authentication authentication) {
        if (authentication == null) {
            model.addAttribute("msg", "Unauthenticated");
            return "error";
        }
        Optional<Painting> op = paintingRepository.findById(id);
        if (op.isEmpty()) {
            model.addAttribute("msg", "Painting not found: " + id);
            return "error";
        }

        model.addAttribute("buyPaintingForm", op.get());
        model.addAttribute("userEmail", ((UserPrincipal)authentication.getPrincipal()).getEmail());
        model.addAttribute("userFullName", ((UserPrincipal)authentication.getPrincipal()).getFullName());
        return "buyPainting";
    }
}
