package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import androidx.annotation.NonNull;

/**
 * A data class representing a location settings states from a {@link LocationSettingsResponse}
 *
 * @since 1.1.0
 */
public interface LocationSettingsStates {

    /**
     * Indicates if the location is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isLocationPresent();

    /**
     * Indicates if the location is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isLocationUsable();

    /**
     * Indicates if the GPS provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isGpsPresent();

    /**
     * Indicates if the GPS provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isGpsUsable();

    /**
     * Indicates if the network location provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isNetworkLocationPresent();

    /**
     * Indicates if the location is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isNetworkLocationUsable();

    /**
     * Indicates if BLE is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isBlePresent();

    /**
     * Indicates if BLE is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isBleUsable();

}
