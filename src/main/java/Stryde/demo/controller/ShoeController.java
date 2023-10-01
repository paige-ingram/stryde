package Stryde.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Stryde.demo.model.Shoe;
import Stryde.demo.service.ShoeService;

@RestController
@RequestMapping("/api/shoes")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @PostMapping("/add")
    public Shoe addShoe(@RequestBody Shoe shoe) {
        return shoeService.saveShoe(shoe);
    }
    @PutMapping("/logRun/{id}")
    public Shoe logRun(@PathVariable Long id, @RequestBody Double addedMileage) {
        return shoeService.logRun(id, addedMileage);
    }
    @GetMapping("/{id}/mileage")
    public Double getTotalMileage(@PathVariable Long id) {
        return shoeService.getTotalMileage(id);
    }
}
