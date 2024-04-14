package lyngby.dk.ENtities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vitamen")

public class HealthProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String name;
    private double calories;
    private double price;
    private String description;
    @Column(name = "expire_date")
    private LocalDate expireDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "storage_id")
    private Storage storage;


    public void addStorage(Storage storage) {
        if (storage != null) {
            this.storage = storage;
            storage.getHealthProducts().add(this);
        }
    }
    public HealthProduct(String category, String name, double calories, double price, String description, LocalDate expireDate) {
        this.category = category;
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.description = description;
        this.expireDate = expireDate;
    }
}
