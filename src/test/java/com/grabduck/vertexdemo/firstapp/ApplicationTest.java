package com.grabduck.vertexdemo.firstapp;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(VertxUnitRunner.class)
public class ApplicationTest {
    private Vertx vertx;

    @org.junit.Before
    public void setUp(TestContext context) throws Exception {
        vertx = Vertx.vertx();
        vertx.deployVerticle(Application.class.getName(), context.asyncAssertSuccess());
    }

    @org.junit.After
    public void tearDown(TestContext context) throws Exception {
        vertx.close(context.asyncAssertSuccess());
    }

    @org.junit.Test
    public void testStart(TestContext context) throws Exception {
        Async async = context.async();
        
        vertx
                .createHttpClient()
                .getNow(8080, "localhost", "/", response -> response.handler(body -> {
                    context.assertTrue(body.toString().contains("Ok"));
                    async.complete(); 
                }));
    }
}