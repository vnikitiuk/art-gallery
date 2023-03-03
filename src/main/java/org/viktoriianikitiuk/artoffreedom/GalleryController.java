package org.viktoriianikitiuk.artoffreedom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    String gallery(Model model) {
        model.addAttribute("paintings", Arrays.asList());
        return "gallery";
    }


}
