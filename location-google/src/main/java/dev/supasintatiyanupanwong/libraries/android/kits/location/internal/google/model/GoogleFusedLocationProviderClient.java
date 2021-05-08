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

package dev.supasintatiyanupanwong.libraries.android.kits.location.internal.google.model;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;

import dev.supasintatiyanupanwong.libraries.android.kits.internal.google.tasks.GoogleTask;
import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.interceptors.LocationResultInterceptor;
import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.interceptors.VoidResultInterceptor;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.FusedLocationProviderClient;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationCallback;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationListener;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationRequest;
import dev.supasintatiyanupanwong.libraries.android.kits.tasks.Task;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class GoogleFusedLocationProviderClient implements FusedLocationProviderClient {

    private final com.google.android.gms.location.FusedLocationProviderClient mDelegate;

    private final GoogleLocationCallbacksHolder mLocationCallbacksHolder =
            new GoogleLocationCallbacksHolder();

    private final GoogleLocationListenersHolder mLocationListenersHolder =
            new GoogleLocationListenersHolder();

    public GoogleFusedLocationProviderClient(@NonNull Context context) {
        mDelegate = com.google.android.gms.location.LocationServices
                .getFusedLocationProviderClient(context);
    }

    public GoogleFusedLocationProviderClient(@NonNull Activity activity) {
        mDelegate = com.google.android.gms.location.LocationServices
                .getFusedLocationProviderClient(activity);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Location> getLastLocation() {
        return new GoogleTask<>(
                mDelegate.getLastLocation(),
                LocationResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationCallback callback) {
        return requestLocationUpdates(request, callback, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener) {
        return requestLocationUpdates(request, listener, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            final @NonNull LocationCallback callback, @Nullable Looper looper) {
        return new GoogleTask<>(
                mDelegate.requestLocationUpdates(
                        GoogleLocationRequest.unwrap(request),
                        mLocationCallbacksHolder.put(callback),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener, @Nullable Looper looper) {
        return new GoogleTask<>(
                mDelegate.requestLocationUpdates(
                        GoogleLocationRequest.unwrap(request),
                        mLocationListenersHolder.put(listener),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationCallback callback) {
        return new GoogleTask<>(
                mDelegate.removeLocationUpdates(mLocationCallbacksHolder.remove(callback)),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationListener listener) {
        return new GoogleTask<>(
                mDelegate.removeLocationUpdates(mLocationListenersHolder.remove(listener)),
                VoidResultInterceptor.INSTANCE
        );
    }

}
