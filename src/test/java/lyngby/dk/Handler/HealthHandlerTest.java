package lyngby.dk.Handler;

import static org.junit.jupiter.api.Assertions.*;

import lyngby.dk.Dao.HealthProductMockDao;
import lyngby.dk.Dto.HealthProductDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class HealthHandlerTest {

    @Test
    public void testCreate() {
        HealthProductMockDao healthHandler = new HealthProductMockDao();

        // Set up a mock set of products (or use a test double)
        Set<HealthProductDTO> products = new HashSet<>();

        // Set the products in the healthHandler (if needed)
        // healthHandler.setProducts(products);

        // Create a mock HealthProductDTO object for the first product
        HealthProductDTO healthProduct1 = new HealthProductDTO();
        healthProduct1.setCategory("Vitamins");
        healthProduct1.setName("Vitamin C");
        healthProduct1.setPrice(5);
        healthProduct1.setCalories(0);
        healthProduct1.setDescription("Vitamin C supplement for immunity support");
        healthProduct1.setDate("2024-04-12");

        // Call the create method for the first product
        HealthProductDTO result1 = healthHandler.create(healthProduct1);

        // Verify that the first product is created successfully
        assertNotNull(result1);
        assertEquals(6, result1.getId()); // Assuming the first product has ID 1

        // Create a mock HealthProductDTO object for the second product
        HealthProductDTO healthProduct2 = new HealthProductDTO();
        healthProduct2.setCategory("Supplements");
        healthProduct2.setName("Probiotics");
        healthProduct2.setPrice(8);
        healthProduct2.setCalories(0);
        healthProduct2.setDescription("Probiotic supplement for gut health");
        healthProduct2.setDate("2024-04-15");

        // Call the create method for the second product
        HealthProductDTO result2 = healthHandler.create(healthProduct2);

        // Verify that the second product is created successfully
        assertNotNull(result2);
        assertEquals(7, result2.getId()); // Assuming the first product has ID 1
    }
    private final String BASE_URL = "http://localhost:7000"; // Adjust the base URL according to your server configuration



}