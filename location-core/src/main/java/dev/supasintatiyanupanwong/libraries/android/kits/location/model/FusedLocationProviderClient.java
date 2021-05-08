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

import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import dev.supasintatiyanupanwong.libraries.android.kits.tasks.Task;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * The main entry point for interacting with the fused location provider.
 *
 * @since 1.0.0
 */
public interface FusedLocationProviderClient {

    /**
     * Requests the best most recent location currently available.
     * <p>
     * If a location is not available, which should happen very rarely, {@code null} will be
     * returned. The best accuracy available while respecting the location permissions will be
     * returned.
     * <p>
     * This method provides a simplified way to get location. It is particularly well suited for
     * applications that do not require an accurate location and that do not want to maintain
     * extra logic for location updates.
     *
     * @return a {@code Task} for the call
     * @since 1.0.0
     */
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    @NonNull Task<Location> getLastLocation();

    /**
     * Requests location updates with a callback on the specified {@code Looper} thread.
     * <p>
     * Any previous {@link LocationRequest}s registered on this {@link LocationListener} will be
     * replaced.
     * <p>
     * Callbacks for {@link LocationCallback} will be made on the calling thread, which must
     * already be a prepared looper thread.
     *
     * @param request The location request for the updates.
     * @param callback The callback for a the location updates.
     * @return a {@code Task} for the call
     * @since 1.0.0
     */
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    @NonNull Task<Void> requestLocationUpdates(
            @NonNull LocationRequest request,
            @NonNull LocationCallback callback);

    /**
     * Requests location updates with a callback on the specified {@code Looper} thread.
     * <p>
     * Any previous {@link LocationRequest}s registered on this {@link LocationCallback} will be
     * replaced.
     * <p>
     * Callbacks for {@link LocationCallback} will be made on the calling thread, which must
     * already be a prepared looper thread.
     *
     * @param request The location request for the updates.
     * @param listener The listener for a the location updates.
     * @return a {@code Task} for the call
     * @since 1.0.0
     */
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    @NonNull Task<Void> requestLocationUpdates(
            @NonNull LocationRequest request,
            @NonNull LocationListener listener);

    /**
     * Requests location updates with a callback on the specified {@code Looper} thread.
     * <p>
     * Any previous {@link LocationRequest}s registered on this {@link LocationCallback} will be
     * replaced.
     * <p>
     * Callbacks for {@link LocationCallback} will be made on the specified thread, which must
     * already be a prepared looper thread.
     *
     * @param request The location request for the updates.
     * @param callback The callback for a the location updates.
     * @param looper The Looper object whose message queue will be used to implement the callback
     * mechanism, or {@code null} to make callbacks on the calling thread.
     * @return a {@code Task} for the call
     * @since 1.0.0
     */
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    @NonNull Task<Void> requestLocationUpdates(
            @NonNull LocationRequest request,
            @NonNull LocationCallback callback,
            @Nullable Looper looper);

    /**
     * Requests location updates with a callback on the specified {@code Looper} thread.
     * <p>
     * Any previous {@link LocationRequest}s registered on this {@link LocationListener} will be
     * replaced.
     * <p>
     * Callbacks for {@link LocationCallback} will be made on the specified thread, which must
     * already be a prepared looper thread.
     *
     * @param request The location request for the updates.
     * @param listener The listener for a the location updates.
     * @param looper The Looper object whose message queue will be used to implement the callback
     * mechanism, or {@code null} to make callbacks on the calling thread.
     * @return a {@code Task} for the call
     * @since 1.0.0
     */
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    @NonNull Task<Void> requestLocationUpdates(
            @NonNull LocationRequest request,
            @NonNull LocationListener listener,
            @Nullable Looper looper);

    /**
     * Removes all location updates for the given location result listener.
     *
     * @param callback the {@link LocationCallback} that was used in {@link
     * #requestLocationUpdates(LocationRequest, LocationCallback)} or {@link
     * #requestLocationUpdates(LocationRequest, LocationCallback, Looper)}.
     * @return a {@code Task} for the call, check {@code isSuccessful()} to determine if it was
     * successful.
     * @since 1.0.0
     */
    @NonNull Task<Void> removeLocationUpdates(@NonNull LocationCallback callback);

    /**
     * Removes all location updates for the given location listener.
     *
     * @param listener the {@link LocationListener} that was used in {@link
     * #requestLocationUpdates(LocationRequest, LocationListener)} or {@link
     * #requestLocationUpdates(LocationRequest, LocationListener, Looper)}.
     * @return a {@code Task} for the call, check {@code isSuccessful()} to determine if it was
     * successful.
     * @since 1.0.0
     */
    @NonNull Task<Void> removeLocationUpdates(@NonNull LocationListener listener);

}
