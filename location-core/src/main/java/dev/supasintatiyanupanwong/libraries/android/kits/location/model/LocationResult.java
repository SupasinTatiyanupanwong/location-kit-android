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

package dev.supasintatiyanupanwong.libraries.android.kits.location.model;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * A data class representing a geographic location result from the fused location provider.
 * <p>
 * All locations returned by {@link #getLocations()} are guaranteed to have a valid latitude,
 * longitude, and UTC timestamp. They are also guaranteed to have elapsed real-time since boot.
 * All other parameters are optional.
 *
 * @since 1.0.0
 */
public interface LocationResult {

    /**
     * Returns the most recent location available in this result, or {@code null} if no locations
     * are available.
     *
     * @since 1.0.0
     */
    @Nullable Location getLastLocation();

    /**
     * Returns locations computed, ordered from oldest to newest.
     * <p>
     * No duplicate locations will be returned to any given listener (i.e. locations will not
     * overlap in time between subsequent calls to a listener).
     *
     * @since 1.0.0
     */
    @NonNull List<Location> getLocations();

}
