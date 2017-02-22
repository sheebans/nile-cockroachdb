package org.gooru.nile.cockroach.processors;

import java.util.ResourceBundle;

import org.gooru.nile.cockroach.constants.MessageConstants;
import org.gooru.nile.cockroach.processors.repositories.RepoBuilder;
import org.gooru.nile.cockroach.processors.responses.MessageResponse;
import org.gooru.nile.cockroach.processors.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

class MessageProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
    private final Message<Object> message;
    private String userId;
    private JsonObject request;

    MessageProcessor(Message<Object> message) {
        this.message = message;
    }

    @Override
    public MessageResponse process() {
        MessageResponse result;
        try {
            final String msgOp = message.headers().get(MessageConstants.MSG_HEADER_OP);
            switch (msgOp) {
            case MessageConstants.MSG_OP_USER_CREATE:
                result = processUserCreate();
                break;
            default:
                LOGGER.error("Invalid operation type passed in, not able to handle");
                return MessageResponseFactory
                    .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.operation"));
            }
            return result;
        } catch (Throwable e) {
            LOGGER.error("Unhandled exception in processing", e);
            return MessageResponseFactory.createInternalErrorResponse();
        }

    }

    private MessageResponse processUserCreate() {
        ProcessorContext context = createContext();

        return RepoBuilder.buildQuestionRepo(context).createUser();
    }

    private ProcessorContext createContext() {
        String userId = message.headers().get(MessageConstants.USER_ID);
        request = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_HTTP_BODY);
        
        return new ProcessorContext(userId, request);
    }

}
