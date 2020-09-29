package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;

public final class GoogleLocationListenersHolder {

    private final Map<LocationListener, com.google.android.gms.location.LocationCallback>
            mListeners = new HashMap<>();

    GoogleLocationListenersHolder() {}

    public @NonNull com.google.android.gms.location.LocationCallback put(
            final @NonNull LocationListener listener) {
        final com.google.android.gms.location.LocationCallback delegate =
                new com.google.android.gms.location.LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult result) {
                        if (result != null) {
                            Location location = result.getLastLocation();
                            if (location != null) {
                                listener.onLocationChanged(location);
                            }
                        }
                    }
                };

        mListeners.put(listener, delegate);

        return delegate;
    }

    public @NonNull com.google.android.gms.location.LocationCallback remove(
            @NonNull LocationListener listener) {
        return Objects.requireNonNull(mListeners.remove(listener));
    }

}
