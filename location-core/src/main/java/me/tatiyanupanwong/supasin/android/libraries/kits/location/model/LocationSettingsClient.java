package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import androidx.annotation.NonNull;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

/**
 * The main entry point for interacting with the settings client.
 *
 * @since 1.1.0
 */
public interface LocationSettingsClient {

    /**
     * Requests the current location settings in order to check if something is wrong.
     * For example, a missing permission, or
     * if the location setting isn't enabled on the device.
     *
     * @return a {@code Task} for the call
     * @since 1.1.0
     */
    @NonNull Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request);

}
