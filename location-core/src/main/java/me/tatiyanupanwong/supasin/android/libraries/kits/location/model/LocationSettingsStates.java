package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import androidx.annotation.NonNull;

/**
 * A data class representing a location settings states from a {@link LocationSettingsResponse}
 *
 * @since 1.1.0
 */
public interface LocationSettingsStates {

    @NonNull Boolean isLocationPresent();

    @NonNull Boolean isLocationUsable();

    @NonNull Boolean isGpsPresent();

    @NonNull Boolean isGpsUsable();

    @NonNull Boolean isNetworkLocationPresent();

    @NonNull Boolean isNetworkLocationUsable();

    @NonNull Boolean isBlePresent();

    @NonNull Boolean isBleUsable();

}
