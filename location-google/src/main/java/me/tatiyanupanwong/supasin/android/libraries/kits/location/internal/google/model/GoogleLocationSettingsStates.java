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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsStates;

public final class GoogleLocationSettingsStates implements LocationSettingsStates {

    private final com.google.android.gms.location.LocationSettingsStates mDelegate;

    private GoogleLocationSettingsStates(
            @NonNull com.google.android.gms.location.LocationSettingsStates delegate) {
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

        GoogleLocationSettingsStates that = (GoogleLocationSettingsStates) obj;

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


    public static @NonNull LocationSettingsStates wrap(
            @NonNull com.google.android.gms.location.LocationSettingsStates delegate) {
        return new GoogleLocationSettingsStates(delegate);
    }

    public static @NonNull List<LocationSettingsStates> wrap(
            @NonNull List<com.google.android.gms.location.LocationSettingsStates> delegates) {
        List<LocationSettingsStates> list = new ArrayList<>();
        for (int iter = 0, size = delegates.size(); iter < size; iter++) {
            list.add(GoogleLocationSettingsStates.wrap(delegates.get(iter)));
        }
        return list;
    }

}
