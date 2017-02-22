package org.gooru.nile.utils.routes.utils;

import java.util.Map.Entry;

import org.gooru.nile.cockroach.constants.MessageConstants;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public final class RouteRequestUtility {

    public static JsonObject getBodyForMessage(RoutingContext routingContext) {
        JsonObject result = new JsonObject();
        JsonObject httpBody = null;
        if (!routingContext.request().method().name().equals(HttpMethod.GET.name())
            && routingContext.getBody().length() > 0) {
            httpBody = routingContext.getBodyAsJson();
        }
        if (httpBody != null) {
            result.put(MessageConstants.MSG_HTTP_BODY, httpBody);
        }

        MultiMap params = routingContext.request().params();
        if (params != null && params.size() > 0) {
            JsonObject paramsAsJson = new JsonObject();
            for (Entry<String, String> param : params) {
                paramsAsJson.put(param.getKey(), param.getValue());
            }
        }
        return result;
    }

    private RouteRequestUtility() {
        throw new AssertionError();
    }

}
