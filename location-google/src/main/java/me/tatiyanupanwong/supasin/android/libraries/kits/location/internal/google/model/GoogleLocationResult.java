package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationResult;

public final class GoogleLocationResult implements LocationResult {

    private final com.google.android.gms.location.LocationResult mDelegate;

    private GoogleLocationResult(
            @NonNull com.google.android.gms.location.LocationResult delegate) {
        mDelegate = delegate;
    }

    @Override
    public @Nullable Location getLastLocation() {
        return mDelegate.getLastLocation();
    }

    @Override
    public @NonNull List<Location> getLocations() {
        return mDelegate.getLocations();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GoogleLocationResult that = (GoogleLocationResult) obj;

        return mDelegate.equals(that.mDelegate);
    }

    @Override
    public int hashCode() {
        return mDelegate.hashCode();
    }

    @Override
    public @NonNull String toString() {
        return mDelegate.toString();
    }


    public static @NonNull LocationResult wrap(
            @NonNull com.google.android.gms.location.LocationResult delegate) {
        return new GoogleLocationResult(delegate);
    }

}
