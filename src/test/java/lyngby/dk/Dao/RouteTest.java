package lyngby.dk.Dao;

import io.javalin.Javalin;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManagerFactory;
import lyngby.dk.Handler.HealthHandler;
import lyngby.dk.routes.HealthShopRoutes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class PersonRessourceTest {
    HealthShopRoutes m  = new HealthShopRoutes(emf);
    private static EntityManagerFactory emf;
    private static Javalin app;
    private static HealthHandler healthProductController;
    private static HealthShopRoutes healthProductRoutes;

    @BeforeAll
    static void setUpAll() {
        app = Javalin.create().start(7007);


    }

    @AfterAll
    static void tearDownAll() {
        // Stop the Javalin app
        app.stop();
    }

    @Test
    @DisplayName("Test GET /api/healthshop")

    void testGetHealthShopEndpoint() {
        m.getHealthProductRoutes();
        given()
                .when()
                .get("/api/healthshop")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test POST request")
    void testPostRequest() {
        // Define the JSON payload for your POST request

        RestAssured.given()
                .when()
                .get("/api/healthshop") // Corrected endpoint URL
                .then()
                .statusCode(200); // Assuming that 201 is the status code for successful creation

    }
}
