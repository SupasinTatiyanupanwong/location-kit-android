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

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.mapzen.android.lost.api.LocationServices;
import com.mapzen.android.lost.api.LostApiClient;
import com.tavorlabs.android.libraries.kits.internal.lost.tasks.LostTask;

import java.util.concurrent.CountDownLatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.LocationResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.VoidResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class LostFusedLocationProviderClient implements FusedLocationProviderClient {

    private static final String TAG = LostFusedLocationProviderClient.class.getSimpleName();

    private final com.mapzen.android.lost.api.FusedLocationProviderApi mDelegate;

    private final LostLocationCallbacksHolder mLocationCallbacksHolder =
            new LostLocationCallbacksHolder();

    private final LostLocationListenersHolder mLocationListenersHolder =
            new LostLocationListenersHolder();

    private final LostApiClient mClient;

    private final CountDownLatch latch = new CountDownLatch(1);

    private final LostApiClient.ConnectionCallbacks connectionCallback = new LostApiClient.ConnectionCallbacks() {

        @Override
        public void onConnected() {
            Log.d(TAG, "Location Kit - Lost FLPC: Connected!");
            latch.countDown();
        }

        @Override
        public void onConnectionSuspended() {
            Log.d(TAG, "Location Kit - Lost FLPC: Connection suspended!");
            latch.countDown();
        }

    };

    public LostFusedLocationProviderClient(@NonNull Context context) {
        mDelegate = LocationServices.FusedLocationApi;
        mClient = new LostApiClient.Builder(context)
                .addConnectionCallbacks(connectionCallback)
                .build();
        connectIfNotConnected();
    }

    public LostFusedLocationProviderClient(@NonNull Activity activity) {
        mDelegate = LocationServices.FusedLocationApi;
        mClient = new LostApiClient.Builder(activity)
                .addConnectionCallbacks(connectionCallback)
                .build();
        connectIfNotConnected();
    }

    private void connectIfNotConnected() {
        if (!mClient.isConnected()) {
            mClient.connect();
        }
    }

    private void awaitForClientConnection() throws InterruptedException {
        /* Had to do the latch stuff because sometimes there is a race condition
        * when a task is called (the client is not connected).
        * Perhaps is not the best solution but i didn't figure out other way.
        * Anyway, it seems to be working :)
        */
        latch.await();
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Location> getLastLocation() {
        TaskCompletionSource<Location> taskSource = new TaskCompletionSource<>();
        try {
            awaitForClientConnection();
            taskSource.setResult(mDelegate.getLastLocation(mClient));
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
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
        TaskCompletionSource<Void> taskSource = new TaskCompletionSource<>();
        try {
            awaitForClientConnection();
            mDelegate.requestLocationUpdates(
                    mClient,
                    LostLocationRequest.unwrap(request),
                    mLocationCallbacksHolder.put(callback),
                    looper);
            taskSource.setResult(null);
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener, @Nullable Looper looper) {
        TaskCompletionSource<Void> taskSource = new TaskCompletionSource<>();
        try {
            awaitForClientConnection();
            mDelegate.requestLocationUpdates(
                    mClient,
                    LostLocationRequest.unwrap(request),
                    mLocationListenersHolder.put(listener),
                    looper);
            taskSource.setResult(null);
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationCallback callback) {
        TaskCompletionSource<Void> taskSource = new TaskCompletionSource<>();
        try {
            awaitForClientConnection();
            mDelegate.removeLocationUpdates(mClient, mLocationCallbacksHolder.remove(callback));
            taskSource.setResult(null);
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationListener listener) {
        TaskCompletionSource<Void> taskSource = new TaskCompletionSource<>();
        try {
            awaitForClientConnection();
            mDelegate.removeLocationUpdates(mClient, mLocationListenersHolder.remove(listener));
            taskSource.setResult(null);
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
                VoidResultInterceptor.INSTANCE
        );
    }

}
