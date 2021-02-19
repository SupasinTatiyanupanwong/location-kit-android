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

package com.tavorlabs.android.libraries.kits.location.internal.lost.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsStates;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;

public final class LostLocationSettingsResponse implements LocationSettingsResponse {

    private final com.mapzen.android.lost.api.LocationSettingsResult mDelegate;

    private LostLocationSettingsResponse(
            @NonNull com.mapzen.android.lost.api.LocationSettingsResult delegate) {
        mDelegate = delegate;
    }

    @Override
    public @NonNull LocationSettingsStates getLocationSettingsStatus() {
        return LostLocationSettingsStates.wrap(mDelegate.getLocationSettingsStates());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LostLocationSettingsResponse that = (LostLocationSettingsResponse) obj;

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


    public static @NonNull LocationSettingsResponse wrap(
            @NonNull com.mapzen.android.lost.api.LocationSettingsResult delegate) {
        return new LostLocationSettingsResponse(delegate);
    }

    public static @NonNull com.mapzen.android.lost.api.LocationSettingsResult unwrap(
            @NonNull LocationSettingsResponse wrapped) {
        return ((LostLocationSettingsResponse) wrapped).mDelegate;
    }

}
