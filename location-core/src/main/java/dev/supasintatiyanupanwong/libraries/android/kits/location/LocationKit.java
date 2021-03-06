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

package dev.supasintatiyanupanwong.libraries.android.kits.location;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import dev.supasintatiyanupanwong.libraries.android.kits.location.internal.LocationFactory;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.FusedLocationProviderClient;
import dev.supasintatiyanupanwong.libraries.android.kits.location.model.LocationRequest;

/**
 * The main entry point for Location APIs.
 *
 * @since 1.0.0
 */
public final class LocationKit {

    private static final LocationFactory FACTORY = LocationPlatform.get().getFactory();

    private LocationKit() {}

    /**
     * Create a new instance of {@link FusedLocationProviderClient} for use in a non-activity
     * {@link Context}.
     *
     * @return a new instance of {@link FusedLocationProviderClient} for use in a non-activity
     * {@link Context}.
     * @since 1.0.0
     */
    public static @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Context context) {
        return FACTORY.getFusedLocationProviderClient(context);
    }

    /**
     * Create a new instance of {@link FusedLocationProviderClient} for use in an {@link Activity}.
     *
     * @return a new instance of {@link FusedLocationProviderClient} for use in an {@link Activity}.
     * @since 1.0.0
     */
    public static @NonNull FusedLocationProviderClient getFusedLocationProviderClient(
            @NonNull Activity activity) {
        return FACTORY.getFusedLocationProviderClient(activity);
    }

    /**
     * Create a location request with default parameters.
     * <p>
     * Default parameters are for a block accuracy, slowly updated location. It can then be adjusted
     * as required by the applications before passing to the {@link FusedLocationProviderClient}.
     *
     * @return a new location request with default parameters.
     * @since 1.0.0
     */
    public static @NonNull LocationRequest newLocationRequest() {
        return FACTORY.newLocationRequest();
    }

}
