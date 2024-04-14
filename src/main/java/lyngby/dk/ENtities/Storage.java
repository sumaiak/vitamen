package lyngby.dk.ENtities;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor

@Entity
@Getter
@Setter
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalTime updatedTimeStamp;
    @Column
    private double totalAmount;
    @Column
    private int shelfNumber;

    @OneToMany(mappedBy = "storage",cascade = CascadeType.ALL)
    private Set<HealthProduct> healthProducts = new HashSet<>();
    @PreUpdate
    private void onUpdate() {
        this.updatedTimeStamp = LocalTime.now();
    }
    public Storage(double totalAmount, int shelfNumber) {
        this.updatedTimeStamp = LocalTime.now();
        this.totalAmount = totalAmount;
        this.shelfNumber = shelfNumber;
    }
}

