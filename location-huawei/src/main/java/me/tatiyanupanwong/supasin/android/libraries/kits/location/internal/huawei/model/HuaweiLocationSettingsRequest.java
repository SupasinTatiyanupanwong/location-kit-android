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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsRequest;

public final class HuaweiLocationSettingsRequest implements LocationSettingsRequest {

    private final com.huawei.hms.location.LocationSettingsRequest mDelegate;

    public HuaweiLocationSettingsRequest() {
        this.mDelegate = new com.huawei.hms.location.LocationSettingsRequest.Builder()
                .addLocationRequest(new com.huawei.hms.location.LocationRequest())
                .build();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HuaweiLocationSettingsRequest that = (HuaweiLocationSettingsRequest) obj;

        return mDelegate.equals(that.mDelegate);
    }

    @Override
    public int hashCode() {
        return mDelegate.hashCode();
    }

    @Override
    public @NonNull String toString() {
        return mDelegate.toString();
    }


    public static @NonNull com.huawei.hms.location.LocationSettingsRequest unwrap(
            @NonNull LocationSettingsRequest wrapped) {
        return ((HuaweiLocationSettingsRequest) wrapped).mDelegate;
    }

    public static @NonNull LocationSettingsRequest wrap(
            @NonNull com.huawei.hms.location.LocationSettingsRequest delegate) {
        return new HuaweiLocationSettingsRequest();
    }

}
