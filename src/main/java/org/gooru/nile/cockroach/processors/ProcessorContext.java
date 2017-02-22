package org.gooru.nile.cockroach.processors;

import io.vertx.core.json.JsonObject;

public class ProcessorContext {

    final private String userId;
    final private JsonObject request;

    public ProcessorContext(String userId, JsonObject request) {
        this.userId = userId;
        this.request = request != null ? request.copy() : null;
    }

    public String userId() {
        return this.userId;
    }

    public JsonObject request() {
        return this.request;
    }

}
