package org.gooru.nile.cockroach.processors.repositories;

import org.gooru.nile.cockroach.processors.responses.MessageResponse;

public interface UserRepo {
    
    MessageResponse createUser();

}
