package org.viktoriianikitiuk.artoffreedom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPaintingController {

    @GetMapping("/addpainting")
    String addPainting(Model model){
        model.addAttribute("paintingForm", new Painting());
        return "addPainting";
    }

    @PostMapping("/addpainting")
    String registerPainting(@ModelAttribute Painting painting, Model model) {
        System.out.println(painting.getPaintingName() + " " + painting.getArtistName());
        model.addAttribute("paintingForm", new Painting());
        return "addpainting";
    }

}
