package org.gooru.nile.cockroach.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nile.cockroach.processors.ProcessorContext;

public final class DBHandlerBuilder {

    private DBHandlerBuilder() {
        throw new AssertionError();
    }

    public static DBHandler buildCreateUserHandler(ProcessorContext context) {
        return new CreateUserHandler(context);

    }

}
