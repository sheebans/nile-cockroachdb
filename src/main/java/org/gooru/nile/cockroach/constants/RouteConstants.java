package org.gooru.nile.cockroach.constants;

public final class RouteConstants {

    // Helper constants
    private static final String API_VERSION = "v1";
    private static final String API_BASE_ROUTE = "/api/nile-datascope/" + API_VERSION + '/';
    public static final String API_UTILS_AUTH_ROUTE = "/api/nile-datascope/*";

    // Helper: Operations
    private static final String EMAILS = "users";

    // Actual End Point Constants: Note that constant values may be duplicated
    // but
    // we are going to have individual constant values to work with for each
    // point instead of reusing the same

    public static final String EP_NILE_DATASCOPE_USER = API_BASE_ROUTE + EMAILS;

    public static final long DEFAULT_TIMEOUT = 30000L;

    private RouteConstants() {
        throw new AssertionError();
    }
}
