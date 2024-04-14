package lyngby.dk.routes;

import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import lyngby.dk.ENtities.Populator;
import lyngby.dk.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        //Populate.populateDatabase(emf);
        HealthShopRoutes routes = new HealthShopRoutes(emf);
        Javalin lyngbyHealthStore = Javalin.create().start(7000);
        lyngbyHealthStore.routes(routes.getHealthProductRoutes());



    }
}
