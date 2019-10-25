package com.sidartasilva.projeto001;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;


/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.get("/").handler(rc -> rc.response().end(new JsonObject().put("hello", "project-001")
            .toString()));
        router.get("/:name").handler(rc -> rc.response().end(
            new JsonObject().put("hello", rc.pathParam("name")).toString()));
        
        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8082);
        
    }

}
