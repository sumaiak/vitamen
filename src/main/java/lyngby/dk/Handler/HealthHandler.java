package lyngby.dk.Handler;

import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import lyngby.dk.Dao.HealthProductMockDao;
import lyngby.dk.Dto.HealthProductDTO;
import lyngby.dk.ENtities.HealthProduct;
import lyngby.dk.ENtities.Storage;
import lyngby.dk.Exception.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;





public class HealthHandler implements IHealthProductController {

    private static final Logger logger = LogManager.getLogger(HealthHandler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String timestamp = dateFormat.format(new Date());
    private static EntityManagerFactory emf;

    public HealthHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }



    HealthProductMockDao health = new HealthProductMockDao();

    @Override
    public Handler getAll() {
        return ctx-> {
            Set<HealthProductDTO> dto = health.getAll();
            System.out.println(dto);
            if (dto.isEmpty()) {
                throw new ApiException(404, "users are not found ",timestamp);


            } else {
                ctx.status(200).json(dto);

            }

        };


    }

    @Override
    public Handler getById() {
        return ctx->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO dto = health.getById(id);
            if(dto == null){
                throw new ApiException(404,"id not found",timestamp);


            }else{
                ctx.status(200).json(dto);
            }


        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            try {
                HealthProductDTO healthProductDTO = ctx.bodyAsClass(HealthProductDTO.class);



                healthProductDTO = health.create(healthProductDTO);

                // Return success response with created health product
                ctx.status(200).json(healthProductDTO);
            } catch (Exception e) {
                // Handle any exceptions that occur during the creation process
                ctx.status(500).result("Internal Server Error: " + e.getMessage());
            }
        };
    }


    @Override
    public Handler update() {
        return ctx -> {
            // Extract the ID from the request URL
            int id = Integer.parseInt(ctx.pathParam("id"));

            // Parse the request body into HealthProductDTO
            HealthProductDTO updatedProductDTO = ctx.bodyAsClass(HealthProductDTO.class);

            // Set the ID of the updated product
            updatedProductDTO.setId(id);

            // Attempt to update the health product
            HealthProductDTO updatedProduct = health.update(updatedProductDTO);

            if (updatedProduct != null) {
                // Return the updated product if it was successfully updated
                ctx.status(200).json(updatedProduct);
            } else {
                // Return a 404 status if the product with the given ID is not found
                ctx.status(404);
            }
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO dto = health.getById(id);
            health.delete(id);
            ctx.status(204).json(dto);
        };
    }


    public Handler createStroage(){
        return ctx->{

           Storage s  = ctx.bodyAsClass(Storage.class);
            s = health.create(s);


            ctx.json(s);
                        };
                    }


                };



