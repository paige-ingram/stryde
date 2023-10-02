package Stryde.demo.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity

public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Date purchaseDate;
    private Double initialMileage;
    private Double totalMileage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    // CONSTRUCTOR
    // Default Constructor
    public Shoe() {}

    // Parameterized Constructor
    public Shoe(Long id, String brand, String model, Date purchaseDate, Double initialMileage, Double totalMileage, AppUser appUser) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.initialMileage = initialMileage;
        this.totalMileage = totalMileage;
        this.appUser = appUser;
    }

    // GETTERS
    public Long getId() { return id;}
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public Double getTotalMileage() {
        return totalMileage;
    }
    public Double getInitialMileage() {
        return initialMileage;
    }
    public AppUser getUser() {
        return appUser;
    }

    // SETTERS
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setInitialMileage(Double initialMileage) {
        this.initialMileage = initialMileage;
    }

    public void setTotalMileage(Double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoe shoe = (Shoe) o;
        return id != null && id.equals(shoe.id);
    }
}
