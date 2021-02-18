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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsStates;

public final class LostLocationSettingsStates implements LocationSettingsStates {

    private final com.mapzen.android.lost.api.LocationSettingsStates mDelegate;

    private LostLocationSettingsStates(
            @NonNull com.mapzen.android.lost.api.LocationSettingsStates delegate) {
        mDelegate = delegate;
    }

    @Override
    public @NonNull Boolean isLocationPresent() {
        return mDelegate.isLocationPresent();
    }

    @Override
    public @NonNull Boolean isLocationUsable() {
        return mDelegate.isLocationUsable();
    }

    @Override
    public @NonNull Boolean isGpsPresent() {
        return mDelegate.isGpsPresent();
    }

    @Override
    public @NonNull Boolean isGpsUsable() {
        return mDelegate.isGpsUsable();
    }

    @Override
    public @NonNull Boolean isNetworkLocationPresent() {
        return mDelegate.isNetworkLocationPresent();
    }

    @Override
    public @NonNull Boolean isNetworkLocationUsable() {
        return mDelegate.isNetworkLocationUsable();
    }

    @Override
    public @NonNull Boolean isBlePresent() {
        return mDelegate.isBlePresent();
    }

    @Override
    public @NonNull Boolean isBleUsable() {
        return mDelegate.isBleUsable();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LostLocationSettingsStates that = (LostLocationSettingsStates) obj;

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


    public static @NonNull
    LocationSettingsStates wrap(
            @NonNull com.mapzen.android.lost.api.LocationSettingsStates delegate) {
        return new LostLocationSettingsStates(delegate);
    }

    public static @NonNull List<LocationSettingsStates> wrap(
            @NonNull List<com.mapzen.android.lost.api.LocationSettingsStates> delegates) {
        List<LocationSettingsStates> list = new ArrayList<>();
        for (int iter = 0, size = delegates.size(); iter < size; iter++) {
            list.add(LostLocationSettingsStates.wrap(delegates.get(iter)));
        }
        return list;
    }

}
