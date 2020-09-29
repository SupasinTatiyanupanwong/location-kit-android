package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors;

import android.location.Location;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.internal.ResultInterceptor;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

/**
 * @since 1.0.0
 */
@RestrictTo(LIBRARY)
public final class LocationResultInterceptor extends ResultInterceptor<Location, Location> {

    public static final LocationResultInterceptor INSTANCE = new LocationResultInterceptor();

    private LocationResultInterceptor() {}

    @Override
    public @Nullable Location intercept(@Nullable Location location) {
        return location;
    }

}
