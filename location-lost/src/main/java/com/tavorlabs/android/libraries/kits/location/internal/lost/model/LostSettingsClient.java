/*
 * Copyright 2021 Tavorlabs
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

import com.google.android.gms.tasks.TaskCompletionSource;
import com.mapzen.android.lost.api.LocationServices;
import com.mapzen.android.lost.api.LocationSettingsResult;
import com.mapzen.android.lost.api.LostApiClient;
import com.mapzen.android.lost.api.PendingResult;
import com.tavorlabs.android.libraries.kits.internal.lost.tasks.LostTask;
import com.tavorlabs.android.libraries.kits.location.internal.lost.interceptors.LostSettingsResultInterceptor;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class LostSettingsClient implements LocationSettingsClient {

    private final com.mapzen.android.lost.api.SettingsApi mDelegate;
    private final LostApiClient mClient;

    public LostSettingsClient(@NonNull Context context) {
        mDelegate = LocationServices.SettingsApi;
        mClient = new LostApiClient.Builder(context)
                .build();
    }

    public LostSettingsClient(@NonNull Activity activity) {
        mDelegate = LocationServices.SettingsApi;
        mClient = new LostApiClient.Builder(activity)
                .build();
    }

    @Override
    public @NonNull Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        TaskCompletionSource<LocationSettingsResult> taskSource = new TaskCompletionSource<>();
        try {
            PendingResult<LocationSettingsResult> pendingResult = mDelegate.checkLocationSettings(mClient, LostLocationSettingsRequest.unwrap(request));
            LocationSettingsResult result = pendingResult.await(30L, TimeUnit.SECONDS);
            //TODO: status code check -- Status.RESOLUTION_REQUIRED has weird issues sometimes, even with the location setting enabled
            taskSource.setResult(result);
        } catch (Exception e) {
            taskSource.setException(e);
        }
        return new LostTask<>(
                taskSource.getTask(),
                LostSettingsResultInterceptor.INSTANCE
        );
    }


}
