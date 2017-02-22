package org.gooru.nile.cockroach.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nile.cockroach.processors.ProcessorContext;
import org.gooru.nile.cockroach.processors.events.EventBuilderFactory;
import org.gooru.nile.cockroach.processors.repositories.activejdbc.entities.AJEntityUsers;
import org.gooru.nile.cockroach.processors.responses.ExecutionResult;
import org.gooru.nile.cockroach.processors.responses.MessageResponse;
import org.gooru.nile.cockroach.processors.responses.MessageResponseFactory;

import io.vertx.core.json.JsonObject;

class CreateUserHandler implements DBHandler {

    private final ProcessorContext context;
    private AJEntityUsers users;

    CreateUserHandler(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public ExecutionResult<MessageResponse> checkSanity() {

        return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);

    }

    @Override
    public ExecutionResult<MessageResponse> validateRequest() {
        users = new AJEntityUsers();
        JsonObject request = context.request();
        request.fieldNames().forEach(column -> {             
            users.setString(column, request.getString(column));
        });
        return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);
    }

    @Override
    public ExecutionResult<MessageResponse> executeRequest() {
        users.saveIt();       
        return new ExecutionResult<>(
            MessageResponseFactory.createCreatedResponse(users.getId().toString(),
                EventBuilderFactory.getCreateQuestionEventBuilder(users.getId().toString())),
            ExecutionResult.ExecutionStatus.SUCCESSFUL);
    }

    @Override
    public boolean handlerReadOnly() {
        return false;
    }
    
}
