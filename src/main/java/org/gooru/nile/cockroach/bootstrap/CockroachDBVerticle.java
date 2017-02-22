package org.gooru.nile.cockroach.bootstrap;

import org.gooru.nile.cockroach.bootstrap.shutdown.Finalizer;
import org.gooru.nile.cockroach.bootstrap.shutdown.Finalizers;
import org.gooru.nile.cockroach.bootstrap.startup.Initializer;
import org.gooru.nile.cockroach.bootstrap.startup.Initializers;
import org.gooru.nile.cockroach.constants.MessagebusEndpoints;
import org.gooru.nile.cockroach.processors.ProcessorBuilder;
import org.gooru.nile.cockroach.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

public class CockroachDBVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(CockroachDBVerticle.class);

    @Override
    public void start(Future<Void> voidFuture) throws Exception {

        EventBus eb = vertx.eventBus();
        vertx.executeBlocking(blockingFuture -> {
            startApplication();
            blockingFuture.complete();
        }, startApplicationFuture -> {
            if (startApplicationFuture.succeeded()) {
                eb.consumer(MessagebusEndpoints.MBEP_USERS, message -> {
                    LOGGER.debug("Received message: " + message.body());
                    vertx.executeBlocking(future -> {
                        MessageResponse result = ProcessorBuilder.build(message).process();
                        future.complete(result);
                    }, res -> {
                        MessageResponse result = (MessageResponse) res.result();
                        message.reply(result.reply(), result.deliveryOptions());
                    });
                }).completionHandler(result -> {
                    if (result.succeeded()) {
                        LOGGER.info("User end point ready to listen");
                        voidFuture.complete();
                    } else {
                        LOGGER.error("Error registering the user handler. Halting the User machinery");
                        voidFuture.fail(result.cause());
                        Runtime.getRuntime().halt(1);
                    }
                });
            } else {
                voidFuture.fail("Not able to initialize the User machinery properly");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        shutDownApplication();
        super.stop();
    }

    private void startApplication() {
        Initializers initializers = new Initializers();
        try {
            for (Initializer initializer : initializers) {
                initializer.initializeComponent(vertx, config());
            }
        } catch (IllegalStateException ie) {
            LOGGER.error("Error initializing application", ie);
            Runtime.getRuntime().halt(1);
        }
    }

    private void shutDownApplication() {
        Finalizers finalizers = new Finalizers();
        for (Finalizer finalizer : finalizers) {
            finalizer.finalizeComponent();
        }

    }

}
