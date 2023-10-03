package Stryde.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Stryde.demo.model.Shoe;
import Stryde.demo.service.ShoeService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/shoes")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    // This endpoint serves the index page with a list of shoes
    @GetMapping("/")
    public String index(Model model) {
        List<Shoe> shoes = shoeService.getAllShoes();
        model.addAttribute("shoes", shoes);
        return "index";
    }

    @GetMapping("/logRun")
    public String logRun(Model model) {
//        List<Shoe> shoes = shoeService.getAllShoes();
        model.addAttribute("shoe", new Shoe());
        return "logRun";
    }
    @PostMapping("/api/log")
    public String logRunForm(@ModelAttribute Shoe shoe, @RequestParam("addedMileage") Double addedMileage, RedirectAttributes redirectAttributes) {
        Shoe updatedShoe = shoeService.logRun(shoe.getId(), addedMileage);
        redirectAttributes.addFlashAttribute("message", "Run logged successfully for " + updatedShoe.getModel());
        return "redirect:/shoes/"; // After logging, redirect back to the main index page
    }


    // This endpoint serves the form to add a shoe using Thymeleaf template
    @GetMapping("/addShoe")
    public String addShoeForm(Model model) {
        model.addAttribute("shoe", new Shoe());
        return "addShoe";
    }

    // This is the REST endpoint to save the shoe
    @PostMapping("/api/add")
    public String saveShoe(@ModelAttribute Shoe shoe) {
        shoeService.saveShoe(shoe);
        return "redirect:/shoes/";
    }

    @GetMapping("/getTotalMileage")
    public Double getTotalMileage(@PathVariable Long id) {
        return shoeService.getTotalMileage(id);
    }
}

