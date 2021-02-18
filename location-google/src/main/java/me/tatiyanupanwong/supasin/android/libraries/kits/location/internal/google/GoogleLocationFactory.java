/*
 * Copyright 2020 Supasin Tatiyanupanwong
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
 *
 * EDITED BY Tavorlabs on 2021
 */

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Arrays;
import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.LocationFactory;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleFusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.exception.GoogleLocationApiException;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.exception.GoogleLocationApiResolvableException;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleLocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleLocationSettingsRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
@SuppressWarnings("unused")
public final class GoogleLocationFactory implements LocationFactory {

    private GoogleLocationFactory() {}

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Context context) {
        return new GoogleFusedLocationProviderClient(context);
    }

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Activity activity) {
        return new GoogleFusedLocationProviderClient(activity);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Context context) {
        return new GoogleSettingsClient(context);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Activity activity) {
        return new GoogleSettingsClient(activity);
    }

    @Override
    public @NonNull LocationRequest newLocationRequest() {
        return new GoogleLocationRequest();
    }

    @Override
    public @NonNull LocationSettingsRequest newLocationSettingsRequest() {
        return new GoogleLocationSettingsRequest();
    }

    @Override
    public @NonNull Exception getApiException(@NonNull Exception e) {
        if (e instanceof com.google.android.gms.common.api.ResolvableApiException) {
            return (new GoogleLocationApiResolvableException((com.google.android.gms.common.api.ResolvableApiException) e));
        } else if (e instanceof com.google.android.gms.common.api.ApiException) {
            return (new GoogleLocationApiException((com.google.android.gms.common.api.ApiException) e));
        } else {
            return e;
        }
    }

    public static @Nullable LocationFactory buildIfSupported(@NonNull Context context) {
        final List<Integer> unavailableResults = Arrays.asList(
                ConnectionResult.SERVICE_DISABLED,
                ConnectionResult.SERVICE_MISSING,
                ConnectionResult.SERVICE_INVALID);
        final int result =
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (unavailableResults.contains(result)) {
            return null;
        }

        return new GoogleLocationFactory();
    }

}
