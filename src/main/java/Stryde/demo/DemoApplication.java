package Stryde.demo;

import Stryde.demo.model.AppUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		AppUser testAppUser = new AppUser("paige", "mypassword", "myemail");
		System.out.println(testAppUser.getUsername());
		System.out.println(testAppUser.getEmail());
	}

}
