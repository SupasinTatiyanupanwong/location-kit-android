/*
 * Copyright 2020 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.supasintatiyanupanwong.libraries.android.kits.location.internal.google.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationRequest;

public final class GoogleLocationRequest implements LocationRequest {

    private final com.google.android.gms.location.LocationRequest mDelegate =
            com.google.android.gms.location.LocationRequest.create();

    @Override
    public @NonNull LocationRequest setPriority(int priority) {
        switch (priority) {
            case PRIORITY_HIGH_ACCURACY:
                //noinspection ConstantConditions
                priority = com.google.android.gms.location.LocationRequest
                        .PRIORITY_HIGH_ACCURACY;
                break;

            case PRIORITY_BALANCED_POWER_ACCURACY:
                //noinspection ConstantConditions
                priority = com.google.android.gms.location.LocationRequest
                        .PRIORITY_BALANCED_POWER_ACCURACY;
                break;

            case PRIORITY_LOW_POWER:
                //noinspection ConstantConditions
                priority = com.google.android.gms.location.LocationRequest
                        .PRIORITY_LOW_POWER;
                break;

            case PRIORITY_NO_POWER:
                //noinspection ConstantConditions
                priority = com.google.android.gms.location.LocationRequest
                        .PRIORITY_NO_POWER;
                break;

            default:
                throw new IllegalArgumentException("Unknown priority: " + priority);
        }

        mDelegate.setPriority(priority);
        return this;
    }

    @Override
    public @NonNull LocationRequest setInterval(long millis) {
        mDelegate.setInterval(millis);
        return this;
    }

    @Override
    public @NonNull LocationRequest setFastestInterval(long millis) {
        mDelegate.setFastestInterval(millis);
        return this;
    }

    @Override
    public @NonNull LocationRequest setMaxWaitTime(long millis) {
        mDelegate.setMaxWaitTime(millis);
        return this;
    }

    @Override
    public @NonNull LocationRequest setExpirationDuration(long millis) {
        mDelegate.setExpirationDuration(millis);
        return this;
    }

    @Override
    public @NonNull LocationRequest setExpirationTime(long millis) {
        mDelegate.setExpirationTime(millis);
        return this;
    }

    @Override
    public @NonNull LocationRequest setNumUpdates(int numUpdates) {
        mDelegate.setNumUpdates(numUpdates);
        return this;
    }

    @Override
    public @NonNull LocationRequest setSmallestDisplacement(float smallestDisplacementMeters) {
        mDelegate.setSmallestDisplacement(smallestDisplacementMeters);
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GoogleLocationRequest that = (GoogleLocationRequest) obj;

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


    public static @NonNull com.google.android.gms.location.LocationRequest unwrap(
            @NonNull LocationRequest wrapped) {
        return ((GoogleLocationRequest) wrapped).mDelegate;
    }

}
