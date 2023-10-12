package Stryde.demo.controller;

import Stryde.demo.model.AppUser;
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
        model.addAttribute("shoe", new Shoe());
        return "logRun";
    }
//    @PostMapping("/api/log")
//    public String logRunForm(@RequestParam("shoeName") String shoeName, @RequestParam("addedKms") Double addedKms, RedirectAttributes redirectAttributes) {
//        Shoe updatedShoe = appUser.findShoeByName(shoeName);
//        Double oldKms = updatedShoe.getTotalMileage();
//        updatedShoe.setTotalMileage(oldKms + addedKms);
//        redirectAttributes.addFlashAttribute("message", "Run logged successfully for " + updatedShoe.getName());
//        return "redirect:/shoes/"; // After logging, redirect back to the main index page
//    }

    @PostMapping("/api/log")
    public String logRunForm(@RequestParam("name") String shoeName, @RequestParam("addedKms") Double addedKms, RedirectAttributes redirectAttributes) {
        Shoe updatedShoe = shoeService.findShoeByName(shoeName);  // Assuming you've a method in ShoeService to find a shoe by name

        if (updatedShoe == null) {
            redirectAttributes.addFlashAttribute("error", "Shoe not found!");
            return "redirect:/shoes/";
        }

        Double oldKms = updatedShoe.getTotalMileage();
        updatedShoe.setTotalMileage(oldKms + addedKms);
        shoeService.saveShoe(updatedShoe);
        redirectAttributes.addFlashAttribute("message", "Run logged successfully for " + updatedShoe.getName());
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

