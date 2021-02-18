package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import androidx.annotation.NonNull;

/**
 * A data class representing a settings response from the location settings client.
 *
 * @since 1.1.0
 */
public interface LocationSettingsResponse {

    /**
     * Returns the {@link LocationSettingsStates} resultant object from the {@link LocationSettingsResponse}
     *
     * @since 1.1.0
     */
    @NonNull
    LocationSettingsStates getLocationSettingsStatus();


}
