package org.gooru.nile.cockroach.processors.repositories;

import org.gooru.nile.cockroach.processors.ProcessorContext;
import org.gooru.nile.cockroach.processors.repositories.activejdbc.AJQuestionRepoBuilder;

/**
 * Created by ashish on 11/1/16.
 */
public final class RepoBuilder {

    private RepoBuilder() {
        throw new AssertionError();
    }

    public static UserRepo buildQuestionRepo(ProcessorContext context) {
        return AJQuestionRepoBuilder.buildQuestionRepo(context);
    }
}
