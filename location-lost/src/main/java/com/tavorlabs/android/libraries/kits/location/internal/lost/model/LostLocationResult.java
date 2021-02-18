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
 */

package com.tavorlabs.android.libraries.kits.location.internal.lost.model;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationResult;

public final class LostLocationResult implements LocationResult {

    private final com.mapzen.android.lost.api.LocationResult mDelegate;

    private LostLocationResult(
            @NonNull com.mapzen.android.lost.api.LocationResult delegate) {
        mDelegate = delegate;
    }

    @Override
    public @Nullable Location getLastLocation() {
        return mDelegate.getLastLocation();
    }

    @Override
    public @NonNull List<Location> getLocations() {
        return mDelegate.getLocations();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LostLocationResult that = (LostLocationResult) obj;

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


    public static @NonNull LocationResult wrap(
            @NonNull com.mapzen.android.lost.api.LocationResult delegate) {
        return new LostLocationResult(delegate);
    }

}
