package org.viktoriianikitiuk.artoffreedom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;
import org.viktoriianikitiuk.artoffreedom.model.Painting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminPaintingController {
    @Autowired
    private PaintingRepository paintingRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/upload";

    @GetMapping("/admin/painting")
    String listPaintings(Model model) {
        model.addAttribute("existingPaintings", paintingRepository.findAll());
        model.addAttribute("addPaintingForm", new Painting());
        return "adminPainting";
    }

    @GetMapping("/admin/painting/{id}")
    String editPainting(Model model, @PathVariable("id") Long id) {
        Optional<Painting> op = paintingRepository.findById(id);
        if (op.isEmpty()) {
            model.addAttribute("msg", "Painting not found: " + id);
            return "error";
        }
        model.addAttribute("editPaintingForm", op.get());
        return "adminEditPainting";
    }

    @PostMapping("/admin/painting/{id}")
    String editPaintingPost(Model model, @PathVariable("id") Long id,
                            @ModelAttribute Painting updatedPainting,
                            @RequestParam("image") MultipartFile file) {
        Optional<Painting> op = paintingRepository.findById(id);
        if (op.isEmpty()) {
            model.addAttribute("msg", "Painting not found: " + id);
            return "error";
        }
        Painting p = op.get();

        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, p.getPaintingImage());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
            return "error";
        }

        p.setPaintingName(updatedPainting.getPaintingName());
        p.setArtistName(updatedPainting.getArtistName());
        p.setPrice(updatedPainting.getPrice());
        paintingRepository.save(p);

        return "redirect:/admin/painting";
    }

    @DeleteMapping("/admin/painting/{id}")
    String deletePainting(Model model, @PathVariable("id") Long id) {
        Optional<Painting> op = paintingRepository.findById(id);
        if (op.isEmpty()) {
            model.addAttribute("msg", "Painting not found: " + id);
            return "error";
        }
        Painting p = op.get();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, p.getPaintingImage());
        try {
            Files.delete(fileNameAndPath);
        } catch (IOException e) {
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
            return "error";
        }
        paintingRepository.deleteById(id);

        return "redirect:/admin/painting";
    }

    @PostMapping("/admin/painting")
    String addPainting(Model model,
                       @ModelAttribute Painting newPainting,
                       @RequestParam("image") MultipartFile file) {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            model.addAttribute("msg", e.getMessage());
            e.printStackTrace();
            return "error";
        }

        Painting p = new Painting();
        p.setPaintingName(newPainting.getPaintingName());
        p.setArtistName(newPainting.getArtistName());
        p.setPrice(newPainting.getPrice());
        p.setPaintingImage(fileNameAndPath.getFileName().toString());
        paintingRepository.save(p);

        return "redirect:/admin/painting";
    }

}
