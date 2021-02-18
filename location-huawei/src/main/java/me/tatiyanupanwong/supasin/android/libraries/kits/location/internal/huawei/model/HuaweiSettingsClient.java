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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.model;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.internal.huawei.tasks.HuaweiTask;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.interceptors.HuaweiSettingsResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class HuaweiSettingsClient implements LocationSettingsClient {

    private final com.huawei.hms.location.SettingsClient mDelegate;

    public HuaweiSettingsClient(@NonNull Context context) {
        mDelegate = com.huawei.hms.location.LocationServices
                .getSettingsClient(context);
    }

    public HuaweiSettingsClient(@NonNull Activity activity) {
        mDelegate = com.huawei.hms.location.LocationServices
                .getSettingsClient(activity);
    }

    @Override
    public @NonNull Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        return new HuaweiTask<>(
                mDelegate.checkLocationSettings(HuaweiLocationSettingsRequest.unwrap(request)),
                HuaweiSettingsResultInterceptor.INSTANCE
        );
    }


}
