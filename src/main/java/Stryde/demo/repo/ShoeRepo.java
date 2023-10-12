package Stryde.demo.repo;

import Stryde.demo.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeRepo extends JpaRepository<Shoe, Long> {
    default List<Shoe> findByAppUserId(Long userId) {
        return null;
    }
    Shoe findByName(String name);
}
