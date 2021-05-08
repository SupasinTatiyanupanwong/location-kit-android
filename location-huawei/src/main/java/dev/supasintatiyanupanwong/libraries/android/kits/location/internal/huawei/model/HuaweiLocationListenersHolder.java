/*
 * Copyright 2020 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.supasintatiyanupanwong.libraries.android.kits.location.internal.huawei.model;

import android.location.Location;

import androidx.annotation.NonNull;

import com.huawei.hms.location.LocationResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationListener;

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
