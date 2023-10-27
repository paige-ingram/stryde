package Stryde.demo.controller;

import Stryde.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/api/log")
    public String logRunForm(@RequestParam("name") String shoeName, @RequestParam("addedKms") Double addedKms, RedirectAttributes redirectAttributes) {
        System.out.println(shoeService.findShoeByName(shoeName));
        Shoe updatedShoe = shoeService.findShoeByName(shoeName);  
        System.out.println(updatedShoe);

        if (updatedShoe == null) {
            redirectAttributes.addFlashAttribute("error", "Shoe not found!");
            return "redirect:/shoes/";
        }
        // System.out.println(updatedShoe.getBrand());
        // System.out.println(updatedShoe.getModel());
        // System.out.println(updatedShoe.getName());
        // System.out.println(updatedShoe.getInitialMileage());


        Double oldKms = updatedShoe.getTotalMileage();
        if (oldKms != null) {
            updatedShoe.setTotalMileage(oldKms + addedKms);
        } else {
            updatedShoe.setTotalMileage(addedKms);
        }
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

