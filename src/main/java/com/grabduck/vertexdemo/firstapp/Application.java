package com.grabduck.vertexdemo.firstapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class Application extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx
                .createHttpServer()
                .requestHandler(r -> r.response().end("Ok"))
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();    
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
