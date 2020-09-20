package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * Used for receiving notifications from the {@link FusedLocationProviderClient} when the location
 * has changed or can no longer be determined. These methods are called if the {@link
 * LocationCallback} has been registered with the location client using the {@link
 * FusedLocationProviderClient#requestLocationUpdates(LocationRequest, LocationCallback)
 * requestLocationUpdates(LocationRequest, LocationCallback)} or {@link
 * FusedLocationProviderClient#requestLocationUpdates(LocationRequest, LocationCallback, Looper)
 * requestLocationUpdates(LocationRequest, LocationCallback, Looper)} methods.
 *
 * @since 1.0.0
 */
public interface LocationCallback {

    /**
     * Called when device location information is available.
     * <p>
     * The most recent location returned by {@link LocationResult#getLastLocation()
     * getLastLocation()} is not guaranteed to be immediately fresh, but will be reasonably up to
     * date given the hints specified by the active {@link LocationRequest}s.
     *
     * @param result The latest location result available.
     * @since 1.0.0
     */
    void onLocationResult(@NonNull LocationResult result);

}
