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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.location.LocationServices;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.internal.google.tasks.GoogleTask;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.interceptors.GoogleSettingsResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class GoogleSettingsClient implements LocationSettingsClient {

    private final com.google.android.gms.location.SettingsClient mDelegate;

    public GoogleSettingsClient(@NonNull Context context) {
        mDelegate = LocationServices
                .getSettingsClient(context);
    }

    public GoogleSettingsClient(@NonNull Activity activity) {
        mDelegate = LocationServices
                .getSettingsClient(activity);
    }

    @Override
    public @NonNull Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        return new GoogleTask<>(
                mDelegate.checkLocationSettings(GoogleLocationSettingsRequest.unwrap(request)),
                GoogleSettingsResultInterceptor.INSTANCE
        );
    }


}
