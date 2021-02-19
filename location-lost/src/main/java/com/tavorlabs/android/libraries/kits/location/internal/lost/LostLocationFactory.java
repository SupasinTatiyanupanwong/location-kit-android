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
 *
 * EDITED BY Tavorlabs on 2021
 */

package com.tavorlabs.android.libraries.kits.location.internal.lost;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.LocationFactory;
import com.tavorlabs.android.libraries.kits.location.internal.lost.model.LostFusedLocationProviderClient;
import com.tavorlabs.android.libraries.kits.location.internal.lost.model.LostLocationRequest;
import com.tavorlabs.android.libraries.kits.location.internal.lost.model.LostLocationSettingsRequest;
import com.tavorlabs.android.libraries.kits.location.internal.lost.model.LostSettingsClient;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
@SuppressWarnings("unused")
public final class LostLocationFactory implements LocationFactory {

    private LostLocationFactory() {}

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Context context) {
        return new LostFusedLocationProviderClient(context);
    }

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Activity activity) {
        return new LostFusedLocationProviderClient(activity);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Context context) {
        return new LostSettingsClient(context);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Activity activity) {
        return new LostSettingsClient(activity);
    }

    @Override
    public @NonNull LocationSettingsRequest newLocationSettingsRequest() {
        return new LostLocationSettingsRequest();
    }

    @Override
    public @NonNull Exception getApiException(@NonNull Exception e) {
        return e;
    }

    @Override
    public @NonNull LocationRequest newLocationRequest() {
        return new LostLocationRequest();
    }


    public static @Nullable LocationFactory buildIfSupported(@NonNull Context context) {
        /*It seems that this does not apply on Lost,
        because it just depends on the Android Location Framework...
        So, this just returns the Factory
         */
        return new LostLocationFactory();
    }

}
