package lyngby.dk.routes;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import lyngby.dk.Exception.ApiException;
import lyngby.dk.Handler.HealthHandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.javalin.apibuilder.ApiBuilder.*;



public class HealthShopRoutes {
    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(HealthShopRoutes.class.getName());


    static {
        String logFilePath = "src/main/resources/log/app.log";

        try {
            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            logger.addHandler(new FileHandler(logFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Error creating log file: " + logFilePath, e);
        }
    }

    public HealthShopRoutes(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private static HealthHandler healthProductController = new HealthHandler(emf);

    public EndpointGroup getHealthProductRoutes() {
        return () -> {
            path("/api/healthshop", () -> {
                get("/", handleExceptions(ctx -> healthProductController.getAll().handle(ctx)));
                get("/{id}", handleExceptions(ctx -> healthProductController.getById().handle(ctx)));
                post("/", handleExceptions(ctx -> healthProductController.create().handle(ctx)));
                put("/{id}", handleExceptions(ctx -> healthProductController.update().handle(ctx)));
                delete("/{id}", handleExceptions(ctx -> healthProductController.delete().handle(ctx)));
            });

            path("/storage", () -> {
                post("/", handleExceptions(ctx -> healthProductController.createStroage().handle(ctx)));
                // Add other storage-related routes here
            });
        };




    }








    private static Handler handleExceptions(Handler handler) {
        return ctx -> {
            try {
                handler.handle(ctx);
            } catch (ApiException e) {
                String response = "{\"status\": \"" + e.getStatusCode() + "\", \"message\": \"" + e.getMessage() + "\", \"timestamp\": \"" + e.getTimeStamp() + "\"}";
                logException(ctx, response);
                ctx.json("{\"status\": \"" + e.getStatusCode() + "\", \"message\": \"" + e.getMessage() + "\", \"timestamp\": \"" + e.getTimeStamp() + "\"}");
            }
        };
    }

    // Helper method to log exceptions
    private static void logException(Context ctx, String response) {
        String errorMessage = "Exception occurred while processing request to " + ctx.path() + ". IP Address: " + ctx.ip();
        logger.log(Level.SEVERE, errorMessage, new Exception(response));
    }

}


