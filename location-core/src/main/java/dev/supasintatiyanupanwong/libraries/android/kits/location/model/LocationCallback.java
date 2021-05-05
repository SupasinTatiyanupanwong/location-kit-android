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

package dev.supasintatiyanupanwong.libraries.android.kits.location.model;

import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * Used for receiving notifications from the {@link FusedLocationProviderClient} when the location
 * has changed or can no longer be determined. These methods are called if the {@link
 * LocationCallback} has been registered with the location client using the {@link
 * FusedLocationProviderClient#requestLocationUpdates(LocationRequest, LocationCallback)
 * requestLocationUpdates(LocationRequest, LocationCallback)} or {@link
 * FusedLocationProviderClient#requestLocationUpdates(LocationRequest, LocationCallback, Looper)
 * requestLocationUpdates(LocationRequest, LocationCallback, Looper)} methods.
 *
 * @since 1.0.0
 */
public interface LocationCallback {

    /**
     * Called when device location information is available.
     * <p>
     * The most recent location returned by {@link LocationResult#getLastLocation()
     * getLastLocation()} is not guaranteed to be immediately fresh, but will be reasonably up to
     * date given the hints specified by the active {@link LocationRequest}s.
     *
     * @param result The latest location result available.
     * @since 1.0.0
     */
    void onLocationResult(@NonNull LocationResult result);

}
