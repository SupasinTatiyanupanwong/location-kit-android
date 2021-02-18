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

import android.util.Log;

import androidx.annotation.NonNull;

import com.mapzen.android.lost.api.LocationAvailability;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;

public final class LostLocationCallbacksHolder {

    private static final String TAG = LostLocationCallbacksHolder.class.getSimpleName();

    private final Map<LocationCallback, com.mapzen.android.lost.api.LocationCallback>
            mCallbacks = new HashMap<>();

    LostLocationCallbacksHolder() {}

    public @NonNull com.mapzen.android.lost.api.LocationCallback put(
            final @NonNull LocationCallback callback) {
        final com.mapzen.android.lost.api.LocationCallback delegate =
                new com.mapzen.android.lost.api.LocationCallback() {

                    @Override
                    public void onLocationAvailability(LocationAvailability locationAvailability) {
                        Log.d(TAG, "onLocationAvailability result = " + locationAvailability.isLocationAvailable());
                    }

                    @Override
                    public void onLocationResult(com.mapzen.android.lost.api.LocationResult result) {
                        callback.onLocationResult(LostLocationResult.wrap(result));
                    }

                };

        mCallbacks.put(callback, delegate);

        return delegate;
    }

    public @NonNull com.mapzen.android.lost.api.LocationCallback remove(
            @NonNull LocationCallback callback) {
        return Objects.requireNonNull(mCallbacks.remove(callback));
    }

}
