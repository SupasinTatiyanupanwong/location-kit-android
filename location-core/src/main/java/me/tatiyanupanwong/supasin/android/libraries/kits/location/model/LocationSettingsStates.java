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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.model;

import androidx.annotation.NonNull;

/**
 * A data class representing a location settings states from a {@link LocationSettingsResponse}
 *
 * @since 1.1.0
 */
public interface LocationSettingsStates {

    /**
     * Indicates if the location is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isLocationPresent();

    /**
     * Indicates if the location is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isLocationUsable();

    /**
     * Indicates if the GPS provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isGpsPresent();

    /**
     * Indicates if the GPS provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isGpsUsable();

    /**
     * Indicates if the network location provider is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isNetworkLocationPresent();

    /**
     * Indicates if the location is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isNetworkLocationUsable();

    /**
     * Indicates if BLE is present on the device.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isBlePresent();

    /**
     * Indicates if BLE is enabled and is usable by the app.
     *
     * @since 1.1.0
     */
    @NonNull Boolean isBleUsable();

}
