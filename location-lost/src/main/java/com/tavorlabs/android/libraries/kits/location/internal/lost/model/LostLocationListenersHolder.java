/*
 * Copyright 2020 Tavorlabs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tavorlabs.android.libraries.kits.location.internal.lost.model;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mapzen.android.lost.api.LocationAvailability;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;

public final class LostLocationListenersHolder {

    private static final String TAG = LostLocationListenersHolder.class.getSimpleName();

    private final Map<LocationListener, com.mapzen.android.lost.api.LocationCallback>
            mListeners = new HashMap<>();

    LostLocationListenersHolder() {}

    public @NonNull com.mapzen.android.lost.api.LocationCallback put(
            final @NonNull LocationListener listener) {
        final com.mapzen.android.lost.api.LocationCallback delegate =
                new com.mapzen.android.lost.api.LocationCallback() {

                    @Override
                    public void onLocationAvailability(LocationAvailability locationAvailability) {
                        Log.d(TAG, "onLocationAvailability result = " + locationAvailability.isLocationAvailable());
                    }

                    @Override
                    public void onLocationResult(com.mapzen.android.lost.api.LocationResult result) {
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

    public @NonNull com.mapzen.android.lost.api.LocationCallback remove(
            @NonNull LocationListener listener) {
        return Objects.requireNonNull(mListeners.remove(listener));
    }

}
