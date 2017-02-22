package org.gooru.nile.cockroach.processors.repositories.activejdbc;

import org.gooru.nile.cockroach.processors.ProcessorContext;
import org.gooru.nile.cockroach.processors.repositories.UserRepo;

/**
 * Created by ashish on 11/1/16.
 */
public final class AJQuestionRepoBuilder {

    private AJQuestionRepoBuilder() {
        throw new AssertionError();
    }

    public static UserRepo buildQuestionRepo(ProcessorContext context) {
        return new AJUserRepo(context);
    }
}
