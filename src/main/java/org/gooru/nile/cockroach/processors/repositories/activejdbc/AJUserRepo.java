package org.gooru.nile.cockroach.processors.repositories.activejdbc;

import org.gooru.nile.cockroach.processors.ProcessorContext;
import org.gooru.nile.cockroach.processors.repositories.UserRepo;
import org.gooru.nile.cockroach.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nile.cockroach.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nile.cockroach.processors.responses.MessageResponse;
import org.postgresql.jdbc2.AbstractJdbc2Statement;;

public class AJUserRepo implements UserRepo {
    private final ProcessorContext context;

    public AJUserRepo(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public MessageResponse createUser() {
        return TransactionExecutor.executeTransaction(DBHandlerBuilder.buildCreateUserHandler(context));

    }

}
