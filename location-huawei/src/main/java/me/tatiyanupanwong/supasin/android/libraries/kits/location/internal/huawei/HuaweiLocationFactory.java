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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;

import java.util.Arrays;
import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.LocationFactory;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model.HuaweiFusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.exception.HuaweiLocationApiException;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.exception.HuaweiLocationApiResolvableException;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model.HuaweiLocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model.HuaweiLocationSettingsRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model.HuaweiSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
@SuppressWarnings("unused")
public final class HuaweiLocationFactory implements LocationFactory {

    private HuaweiLocationFactory() {}

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Context context) {
        return new HuaweiFusedLocationProviderClient(context);
    }

    @Override
    public @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Activity activity) {
        return new HuaweiFusedLocationProviderClient(activity);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Context context) {
        return new HuaweiSettingsClient(context);
    }

    @Override
    public @NonNull LocationSettingsClient getLocationSettingsClient(
            @NonNull Activity activity) {
        return new HuaweiSettingsClient(activity);
    }

    @Override
    public @NonNull LocationSettingsRequest newLocationSettingsRequest() {
        return new HuaweiLocationSettingsRequest();
    }

    @Override
    public @NonNull Exception getApiException(@NonNull Exception e) {
        if (e instanceof com.huawei.hms.common.ResolvableApiException) {
            return (new HuaweiLocationApiResolvableException((com.huawei.hms.common.ResolvableApiException) e));
        } else if (e instanceof com.huawei.hms.common.ApiException) {
            return (new HuaweiLocationApiException((com.huawei.hms.common.ApiException) e));
        } else {
            return e;
        }
    }

    @Override
    public @NonNull LocationRequest newLocationRequest() {
        return new HuaweiLocationRequest();
    }


    public static @Nullable LocationFactory buildIfSupported(@NonNull Context context) {
        final List<Integer> unavailableResults = Arrays.asList(
                ConnectionResult.SERVICE_DISABLED,
                ConnectionResult.SERVICE_MISSING,
                ConnectionResult.SERVICE_INVALID);
        final int result =
                HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context);
        if (unavailableResults.contains(result)) {
            return null;
        }

        return new HuaweiLocationFactory();
    }

}
