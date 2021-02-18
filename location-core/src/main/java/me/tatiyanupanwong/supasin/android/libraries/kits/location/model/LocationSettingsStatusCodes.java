package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;


/**
 * A data class representing status codes from a {@link LocationApiException}.
 *
 * @since 1.1.0
 */
public class LocationSettingsStatusCodes {

    private LocationSettingsStatusCodes() {
        throw new IllegalStateException("Utility class");
    }

    public static final int API_NOT_CONNECTED = 17;
    public static final int CANCELED = 16;
    public static final int DEVELOPER_ERROR = 10;
    public static final int ERROR = 13;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 14;
    public static final int INVALID_ACCOUNT = 5;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    /** @deprecated */
    @Deprecated
    public static final int SERVICE_DISABLED = 3;
    /** @deprecated */
    @Deprecated
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int SUCCESS_CACHE = -1;
    public static final int TIMEOUT = 15;

}
