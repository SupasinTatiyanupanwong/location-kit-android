package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * Used for receiving notifications from the {@link FusedLocationProviderClient} when the location
 * has changed. These methods are called if the {@link LocationListener} has been registered with
 * the location client using the {@link FusedLocationProviderClient#requestLocationUpdates(
 * LocationRequest, LocationListener) requestLocationUpdates(LocationRequest, LocationCallback)} or
 * {@link FusedLocationProviderClient#requestLocationUpdates(LocationRequest, LocationListener,
 * Looper) requestLocationUpdates(LocationRequest, LocationCallback, Looper)} methods.
 *
 * @since 1.0.0
 */
public interface LocationListener {

    /**
     * Called when the location has changed.
     *
     * @param location The updated location.
     * @since 1.0.0
     */
    void onLocationChanged(@NonNull Location location);

}
