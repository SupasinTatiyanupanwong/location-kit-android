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

package dev.supasintatiyanupanwong.libraries.android.kits.location.internal.google;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Arrays;
import java.util.List;

import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.LocationFactory;
import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.google.model.GoogleFusedLocationProviderClient;
import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.google.model.GoogleLocationRequest;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.FusedLocationProviderClient;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationRequest;

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
    public @NonNull LocationRequest newLocationRequest() {
        return new GoogleLocationRequest();
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
