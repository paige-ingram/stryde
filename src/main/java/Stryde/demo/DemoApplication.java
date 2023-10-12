package Stryde.demo;

import Stryde.demo.model.AppUser;
import Stryde.demo.model.Shoe;
import Stryde.demo.repo.ShoeRepo;
import Stryde.demo.service.ShoeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		AppUser paige = new AppUser("paige", "mypassword", "myemail");
		Shoe shoe = new Shoe(123L, "favourite shoe", "Hokas", "Clifton", new Date(), 100.00, 400.00, paige);
		System.out.println(paige.getUsername());
		System.out.println(paige.getEmail());
		System.out.println(shoe.getBrand());
		System.out.println(shoe.getModel());
		System.out.println(shoe.getUser());
		System.out.println(shoe.getInitialMileage());
	}

}
