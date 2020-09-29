package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * A data class representing a geographic location result from the fused location provider.
 * <p>
 * All locations returned by {@link #getLocations()} are guaranteed to have a valid latitude,
 * longitude, and UTC timestamp. They are also guaranteed to have elapsed real-time since boot.
 * All other parameters are optional.
 *
 * @since 1.0.0
 */
public interface LocationResult {

    /**
     * Returns the most recent location available in this result, or {@code null} if no locations
     * are available.
     *
     * @since 1.0.0
     */
    @Nullable Location getLastLocation();

    /**
     * Returns locations computed, ordered from oldest to newest.
     * <p>
     * No duplicate locations will be returned to any given listener (i.e. locations will not
     * overlap in time between subsequent calls to a listener).
     *
     * @since 1.0.0
     */
    @NonNull List<Location> getLocations();

}
