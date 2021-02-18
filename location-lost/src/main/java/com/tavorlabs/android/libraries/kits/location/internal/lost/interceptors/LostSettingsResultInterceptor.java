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

package com.tavorlabs.android.libraries.kits.location.internal.lost.interceptors;

import com.tavorlabs.android.libraries.kits.location.internal.lost.model.LostLocationSettingsResponse;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.internal.ResultInterceptor;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

/**
 * @since 1.1.0
 */
@RestrictTo(LIBRARY)
public final class LostSettingsResultInterceptor extends ResultInterceptor<com.mapzen.android.lost.api.LocationSettingsResult, LocationSettingsResponse> {

    public static final LostSettingsResultInterceptor INSTANCE = new LostSettingsResultInterceptor();

    private LostSettingsResultInterceptor() {}

    @Override
    public @Nullable LocationSettingsResponse intercept(@Nullable com.mapzen.android.lost.api.LocationSettingsResult response) {
        if (response != null) {
            return LostLocationSettingsResponse.wrap(response);
        } else {
            return null;
        }
    }

}
