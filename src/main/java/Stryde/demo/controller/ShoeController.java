package Stryde.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Stryde.demo.model.Shoe;
import Stryde.demo.service.ShoeService;

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
        return "index"; // This assumes you've named the Thymeleaf template as 'index.html'
    }

    // This endpoint serves the form to add a shoe using Thymeleaf template
    @GetMapping("/addShoe")
    public String addShoeForm(Model model) {
        model.addAttribute("shoe", new Shoe());
        return "addShoe";
    }

    // This is the REST endpoint to save the shoe
    @PostMapping("/api/add")
    public Shoe saveShoe(@RequestBody Shoe shoe) {
        return shoeService.saveShoe(shoe);
    }

    @PostMapping("/api/logRun/{id}")
    public Shoe logRun(@PathVariable Long id, @RequestParam Double addedMileage) {
        return shoeService.logRun(id, addedMileage);
    }

    @GetMapping("/api/{id}/mileage")
    public Double getTotalMileage(@PathVariable Long id) {
        return shoeService.getTotalMileage(id);
    }
}

