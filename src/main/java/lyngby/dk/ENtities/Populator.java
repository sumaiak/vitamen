package lyngby.dk.ENtities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Populator{

    public static void populateDatabase(EntityManagerFactory emf) {
        HealthProduct healthProduct1 = new HealthProduct("Vitamins", "Multivamins", 20, 25.99, "A comprehensive daily\n" +
                "multivitamin", LocalDate.of(2024, 12, 31));

        HealthProduct healthProduct2 = new HealthProduct("Supplements", "Omega-3", 15, 19.50, "Fish oil supplement rich in\n" +
                "omega-3", LocalDate.of(2025, 06, 30));

        Storage storage1 = new Storage(20, 30);
        Storage storage2 = new Storage(30.5, 31);
        healthProduct1.addStorage(storage1);
        healthProduct2.addStorage(storage2);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(healthProduct1);
            em.persist(healthProduct2);
            em.getTransaction().commit();
        }
    }
}

