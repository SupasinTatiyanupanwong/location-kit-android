package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model;

import androidx.annotation.NonNull;

import com.huawei.hms.location.LocationResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;

public final class HuaweiLocationCallbacksHolder {

    private final Map<LocationCallback, com.huawei.hms.location.LocationCallback>
            mCallbacks = new HashMap<>();

    HuaweiLocationCallbacksHolder() {}

    public @NonNull com.huawei.hms.location.LocationCallback put(
            final @NonNull LocationCallback callback) {
        final com.huawei.hms.location.LocationCallback delegate =
                new com.huawei.hms.location.LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult result) {
                        callback.onLocationResult(HuaweiLocationResult.wrap(result));
                    }
                };

        mCallbacks.put(callback, delegate);

        return delegate;
    }

    public @NonNull com.huawei.hms.location.LocationCallback remove(
            @NonNull LocationCallback callback) {
        return Objects.requireNonNull(mCallbacks.remove(callback));
    }

}
