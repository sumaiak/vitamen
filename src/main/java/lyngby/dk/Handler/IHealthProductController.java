package lyngby.dk.Handler;

import io.javalin.http.Handler;

public interface IHealthProductController {
    Handler getAll();
    Handler getById();
    Handler create();
    Handler update();
    Handler delete();

}
