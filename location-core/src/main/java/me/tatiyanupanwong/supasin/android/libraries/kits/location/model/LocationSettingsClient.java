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
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

/**
 * The main entry point for interacting with the settings client.
 *
 * @since 1.1.0
 */
public interface LocationSettingsClient {

    /**
     * Requests the current location settings in order to check if something is wrong.
     * For example, a missing permission, or
     * if the location setting isn't enabled on the device.
     *
     * @return a {@code Task} for the call
     * @since 1.1.0
     */
    @NonNull Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request);

}
