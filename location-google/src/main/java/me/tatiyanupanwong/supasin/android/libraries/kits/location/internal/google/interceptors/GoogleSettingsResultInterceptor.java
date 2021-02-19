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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.interceptors;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleLocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.internal.ResultInterceptor;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

/**
 * @since 1.1.0
 */
@RestrictTo(LIBRARY)
public final class GoogleSettingsResultInterceptor extends ResultInterceptor<com.google.android.gms.location.LocationSettingsResponse, LocationSettingsResponse> {

    public static final GoogleSettingsResultInterceptor INSTANCE = new GoogleSettingsResultInterceptor();

    private GoogleSettingsResultInterceptor() {}

    @Override
    public @Nullable LocationSettingsResponse intercept(@Nullable com.google.android.gms.location.LocationSettingsResponse response) {
        if (response != null) {
            return GoogleLocationSettingsResponse.wrap(response);
        } else {
            return null;
        }
    }

}
