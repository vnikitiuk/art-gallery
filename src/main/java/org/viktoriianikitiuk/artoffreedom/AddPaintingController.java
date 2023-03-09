package org.viktoriianikitiuk.artoffreedom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AddPaintingController {
    @Autowired
    private PaintingRepository paintingRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/upload";

    @GetMapping("/addpainting")
    String addPainting(Model model){
        model.addAttribute("paintingForm", new Painting());
        return "addPainting";
    }

    @PostMapping("/addpainting")
    String registerPainting(@ModelAttribute Painting painting, Model model, @RequestParam("image") MultipartFile file) {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        try {
            if (!Files.exists(fileNameAndPath.getParent()))
                Files.createDirectories(fileNameAndPath.getParent());
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
            return "error";
        }

        model.addAttribute("paintingForm", new Painting());
        Painting p = new Painting();
        p.setPaintingName(painting.getPaintingName());
        p.setArtistName(painting.getArtistName());
        p.setPrice(painting.getPrice());
        System.out.println(file.getName());
        p.setPaintingImage(fileNameAndPath.getFileName().toString());
        paintingRepository.save(p);
        return "addpainting";
    }

}
