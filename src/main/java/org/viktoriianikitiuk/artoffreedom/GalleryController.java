package org.viktoriianikitiuk.artoffreedom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;

import java.util.Arrays;

@Controller
public class GalleryController {

    @Autowired
    private PaintingRepository paintingRepository;

    @GetMapping("/gallery")
    String gallery(Model model) {
        model.addAttribute("paintings", paintingRepository.findAll());
        return "gallery";
    }


}
