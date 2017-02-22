package org.gooru.nile.cockroach.constants;

public final class MessagebusEndpoints {
    /*
     * Any change here in end points should be done in the gateway side as well,
     * as both sender and receiver should be in sync
     */
    public static final String MBEP_USERS = "org.gooru.nile.message.bus.users";

    private MessagebusEndpoints() {
        throw new AssertionError();
    }
}
