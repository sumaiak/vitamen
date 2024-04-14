package lyngby.dk.Dao;

import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import lyngby.dk.Dto.HealthProductDTO;
import lyngby.dk.ENtities.Storage;
import lyngby.dk.Handler.IHealthProductController;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class HealthProductMockDao implements HealthProductDAOMock {
    private static EntityManagerFactory emf;
    Set<HealthProductDTO> products = new HashSet<>(Set.of(
            new HealthProductDTO(1, "Vitamins", "Multivitamin", 20, 25.99, "A comprehensive daily multivitamin", "2024-12-31"),
            new HealthProductDTO(2, "Supplements", "Omega-3", 15, 19.50, "Fish oil supplement rich in omega-3", "2025-06-30"),
            new HealthProductDTO(3, "Personal Care", "Aloe Vera Gel", 5, 12.99, "Soothing and moisturizing aloe vera gel", "2023-10-15"),
            new HealthProductDTO(4, "Vitamins", "Vitamin C", 0, 9.99, "Immune system support with vitamin C", "2024-08-20"),
            new HealthProductDTO(5, "Supplements", "Protein Powder", 120, 29.99, "Whey protein powder for muscle recovery", "2023-11-30")
    ));

    @Override
    public Set<HealthProductDTO> getAll() {
        return products;
    }

    @Override
    public HealthProductDTO getById(int id) {

        for (HealthProductDTO product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Set<HealthProductDTO> getByCategory(String category) {
        Set<HealthProductDTO> categoryProducts = new HashSet<>();

        for (HealthProductDTO product : products) {
            if (product.getCategory().equals(category)) {
                categoryProducts.add(product);


            }
        }
        return categoryProducts;
    }

    @Override
    public HealthProductDTO create(HealthProductDTO healthProduct) {
        int newId = products.size() + 1;
        healthProduct.setId(newId);

        products.add(healthProduct);

        return healthProduct;

    }

    public HealthProductDTO update(HealthProductDTO healthProduct) {
        for (HealthProductDTO product : products) {
            if (product.getId() == healthProduct.getId()) {
                // Update the attributes of the existing product
                product.setCategory(healthProduct.getCategory());
                product.setName(healthProduct.getName());
                product.setDescription(healthProduct.getDescription());


                return product;
            }
        }
        return null;
    }

    @Override
    public HealthProductDTO delete(int id) {
        Iterator<HealthProductDTO> iterator = products.iterator();
        while (iterator.hasNext()) {
            HealthProductDTO product = iterator.next();
            if (product.getId() == id) {
                // Remove the product from the set
                iterator.remove();
                // Return the deleted product
                return product;
            }
        }
        return null; // Return null if product with given ID is not found
    }

    @Override
    public Set<HealthProductDTO> getTwoWeeksToExpire() {
        Set<HealthProductDTO> expiringProducts = new HashSet<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate twoWeeksLater = currentDate.plusWeeks(2);
        for (HealthProductDTO product : products) {
            LocalDate expireDate = LocalDate.parse(product.getDate());
            if (expireDate.isAfter(currentDate) && expireDate.isBefore(twoWeeksLater)) {
                expiringProducts.add(product);
            }
        }
        return expiringProducts;
    }

    public List<HealthProductDTO> filterEventByStatusAndCategory(String calories) {


        return products.stream()
                .filter(health -> health.getCalories()< 50)
                .collect(Collectors.toList());
    }
    public List<String> getProductNames(List<HealthProductDTO> products) {
        return products.stream()
                .map(HealthProductDTO::getName)
                .collect(Collectors.toList());
    }
    public Map<String, Double> getTotalPriceByCategory(List<HealthProductDTO> products) {
        return products.stream()
                .collect(Collectors.groupingBy(HealthProductDTO::getCategory,
                        Collectors.summingDouble(HealthProductDTO::getPrice)));
    }


    public Storage create(Storage storage) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction().commit();
        }
        return storage;
    }
}

