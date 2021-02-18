package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import android.app.PendingIntent;

import androidx.annotation.NonNull;

/**
 * A data class representing a resolvable exception from the location settings client.
 *
 * @since 1.1.0
 */
public interface LocationApiResolvableException extends LocationApiException {

    /**
     * The status code of the exception. Should be a RESOLUTION_REQUIRED (6) code from {@link LocationSettingsStatusCodes}.
     *
     * @since 1.1.0
     */
    int getStatusCode();

    /**
     * The pending intent of the exception. This should be used later so the user can resolve the exception.
     *
     * @since 1.1.0
     */
    @NonNull PendingIntent getResolution();

}
