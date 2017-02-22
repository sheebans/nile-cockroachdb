package org.gooru.nile.utils.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nile.cockroach.constants.ConfigConstants;
import org.gooru.nile.cockroach.constants.MessageConstants;
import org.gooru.nile.cockroach.constants.MessagebusEndpoints;
import org.gooru.nile.cockroach.constants.RouteConstants;
import org.gooru.nile.utils.routes.utils.RouteRequestUtility;
import org.gooru.nile.utils.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteUserConfigurator implements RouteConfigurator {

    private static final Logger LOG = LoggerFactory.getLogger("org.gooru.nile.cockroach.bootstrap.ServerVerticle");

    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.post(RouteConstants.EP_NILE_DATASCOPE_USER).handler(this::createUser);
    }

    private void createUser(RoutingContext routingContext) {
        final DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_CREATE);
        eb.send(MessagebusEndpoints.MBEP_USERS, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

}
