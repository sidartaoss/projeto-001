package com.sidartasilva.projeto001;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
            .requestHandler(request -> {
                request.response().end(new JsonObject().put("project", "001").toString());
            })
            .listen(8081);
    }

}
