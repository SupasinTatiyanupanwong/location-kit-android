package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model;

import android.location.Location;

import androidx.annotation.NonNull;

import com.huawei.hms.location.LocationResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;

public final class HuaweiLocationListenersHolder {

    private final Map<LocationListener, com.huawei.hms.location.LocationCallback>
            mListeners = new HashMap<>();

    HuaweiLocationListenersHolder() {}

    public @NonNull com.huawei.hms.location.LocationCallback put(
            final @NonNull LocationListener listener) {
        final com.huawei.hms.location.LocationCallback delegate =
                new com.huawei.hms.location.LocationCallback() {
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

    public @NonNull com.huawei.hms.location.LocationCallback remove(
            @NonNull LocationListener listener) {
        return Objects.requireNonNull(mListeners.remove(listener));
    }

}
