package org.gooru.nile.cockroach.processors;

import org.gooru.nile.cockroach.processors.responses.MessageResponse;

public interface Processor {
    MessageResponse process();
}
