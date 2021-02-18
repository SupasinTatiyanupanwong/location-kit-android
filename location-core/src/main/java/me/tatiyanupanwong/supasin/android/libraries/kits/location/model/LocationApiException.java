package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

/**
 * A data class representing a exception from the location settings client.
 *
 * @since 1.1.0
 */
public interface LocationApiException {

    /**
     * The status code of the exception. It should a code from the {@link LocationSettingsStatusCodes} class.
     *
     * @since 1.1.0
     */
    int getStatusCode();

}
