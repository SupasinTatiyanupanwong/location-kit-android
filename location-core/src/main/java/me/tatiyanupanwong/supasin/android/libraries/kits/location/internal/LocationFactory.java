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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * @since 1.0.0
 */
@RestrictTo(LIBRARY_GROUP)
public interface LocationFactory {

    @NonNull FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context context);

    @NonNull FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity activity);

    @NonNull LocationSettingsClient getLocationSettingsClient(@NonNull Context context);

    @NonNull LocationSettingsClient getLocationSettingsClient(@NonNull Activity activity);

    @NonNull LocationRequest newLocationRequest();

    @NonNull LocationSettingsRequest newLocationSettingsRequest();

    @NonNull Exception getApiException(@NonNull Exception ex);

}
