package Stryde.demo.repo;

import Stryde.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    // Custom query methods if needed
}
