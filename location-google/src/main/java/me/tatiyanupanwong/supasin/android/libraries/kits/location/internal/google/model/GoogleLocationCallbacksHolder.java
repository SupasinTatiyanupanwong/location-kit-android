package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;

public final class GoogleLocationCallbacksHolder {

    private final Map<LocationCallback, com.google.android.gms.location.LocationCallback>
            mCallbacks = new HashMap<>();

    GoogleLocationCallbacksHolder() {}

    public @NonNull com.google.android.gms.location.LocationCallback put(
            final @NonNull LocationCallback callback) {
        final com.google.android.gms.location.LocationCallback delegate =
                new com.google.android.gms.location.LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult result) {
                        callback.onLocationResult(GoogleLocationResult.wrap(result));
                    }
                };

        mCallbacks.put(callback, delegate);

        return delegate;
    }

    public @NonNull com.google.android.gms.location.LocationCallback remove(
            @NonNull LocationCallback callback) {
        return Objects.requireNonNull(mCallbacks.remove(callback));
    }

}
