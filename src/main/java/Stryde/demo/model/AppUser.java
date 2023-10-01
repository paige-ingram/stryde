package Stryde.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shoe> shoes = new ArrayList<>();

    // Default Constructor
    public AppUser() {}

    // Parameterized Constructor
    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // GETTERS
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public List<Shoe> getShoes() {
        return shoes;
    }

    // SETTERS
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    // Additional method to add a shoe
    public void addShoe(Shoe shoe) {
        shoes.add(shoe);
        shoe.setUser(this);
    }

    // Additional method to remove a shoe
    public void removeShoe(Shoe shoe) {
        shoes.remove(shoe);
        shoe.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id != null && id.equals(appUser.id);
    }
}
