package org.gooru.nile.utils.responses.auth.transformers;

import io.vertx.core.eventbus.Message;

public class ResponseTransformerBuilder {
    public ResponseTransformer build(Message<Object> message) {
        return new HttpResponseTransformer(message);
    }
}
