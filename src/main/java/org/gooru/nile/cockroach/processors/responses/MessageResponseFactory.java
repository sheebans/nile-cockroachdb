package org.gooru.nile.cockroach.processors.responses;

import org.gooru.nile.cockroach.constants.HttpConstants;
import org.gooru.nile.cockroach.constants.MessageConstants;
import org.gooru.nile.cockroach.processors.events.EventBuilder;

import io.vertx.core.json.JsonObject;


public final class MessageResponseFactory {
    private MessageResponseFactory() {
        throw new AssertionError();
    }

    public static MessageResponse createInvalidRequestResponse() {
        return new MessageResponse.Builder().failed().setStatusBadRequest().build();
    }

    public static MessageResponse createForbiddenResponse() {
        return new MessageResponse.Builder().failed().setStatusForbidden().build();
    }

    public static MessageResponse createInternalErrorResponse() {
        return new MessageResponse.Builder().failed().setStatusInternalError().build();
    }

    public static MessageResponse createInvalidRequestResponse(String message) {
        return new MessageResponse.Builder().failed().setStatusBadRequest()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message)).build();
    }

    public static MessageResponse createForbiddenResponse(String message) {
        return new MessageResponse.Builder().failed().setStatusForbidden()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message)).build();
    }

    public static MessageResponse createInternalErrorResponse(String message) {
        return new MessageResponse.Builder().failed().setStatusInternalError()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message)).build();
    }

    public static MessageResponse createNotFoundResponse(String message) {
        return new MessageResponse.Builder().failed().setStatusNotFound()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message)).build();
    }

    public static MessageResponse createValidationErrorResponse(JsonObject errors) {
        return new MessageResponse.Builder().validationFailed().setStatusBadRequest().setResponseBody(errors).build();

    }

    public static MessageResponse createCreatedResponse(String location, EventBuilder eventBuilder) {
        return new MessageResponse.Builder().successful().setStatusCreated()
            .setHeader(HttpConstants.HEADER_LOCATION, location).setEventData(eventBuilder.build()).build();
    }

    public static MessageResponse createNoContentResponse(String message) {
        return new MessageResponse.Builder().successful().setStatusNoOutput()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message)).build();
    }

    public static MessageResponse createNoContentResponse(String message, EventBuilder eventBuilder) {
        return new MessageResponse.Builder().successful().setStatusNoOutput()
            .setResponseBody(new JsonObject().put(MessageConstants.MSG_MESSAGE, message))
            .setEventData(eventBuilder.build()).build();
    }

    public static MessageResponse createOkayResponse(JsonObject body) {
        return new MessageResponse.Builder().successful().setStatusOkay().setResponseBody(body).build();
    }

}
