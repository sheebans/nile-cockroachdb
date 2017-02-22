package org.gooru.nile.cockroach.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nile.cockroach.processors.responses.ExecutionResult;
import org.gooru.nile.cockroach.processors.responses.MessageResponse;

/**
 * Created by ashish on 11/1/16.
 */
public interface DBHandler {
    ExecutionResult<MessageResponse> checkSanity();

    ExecutionResult<MessageResponse> validateRequest();

    ExecutionResult<MessageResponse> executeRequest();

    boolean handlerReadOnly();
}
