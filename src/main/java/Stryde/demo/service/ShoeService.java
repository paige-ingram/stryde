package Stryde.demo.service;

import Stryde.demo.model.Shoe;
import Stryde.demo.repo.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {
    @Autowired
    private ShoeRepo shoeRepo;

    public Shoe logRun(Long id, Double addedMileage) {
        Shoe shoe = shoeRepo.findById(id).orElseThrow(() -> new RuntimeException("Shoe not found"));
        shoe.setTotalMileage(shoe.getTotalMileage() + addedMileage);
        return shoeRepo.save(shoe);
    }

    public Shoe saveShoe(Shoe shoe) {
        try {
            return shoeRepo.save(shoe);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception, e.g., log it, and/or rethrow a custom exception
            throw new RuntimeException("Failed to save shoe", e);
        }
    }

    public Double getTotalMileage(Long id) {
        // Fetch the shoe by its ID
        Shoe shoe = shoeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Shoe with ID " + id + " not found"));
        // Return its total mileage
        return shoe.getTotalMileage();
    }

    public List<Shoe> getAllShoes() {
        return shoeRepo.findAll();
    }

}
