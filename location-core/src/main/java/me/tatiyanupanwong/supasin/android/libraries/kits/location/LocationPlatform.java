/*
 * Copyright 2020 Supasin Tatiyanupanwong
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
 *
 * EDITED by Tavorlabs on 2021
 */

package me.tatiyanupanwong.supasin.android.libraries.kits.location;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.LocationFactory;

/**
 * @since 1.0.0
 */
abstract class LocationPlatform {

    private static final String TAG = LocationPlatform.class.getSimpleName();

    private static LocationPlatform sPlatform;

    static synchronized void init(@NonNull Context context) {
        sPlatform = findPlatform(context);
    }

    static synchronized LocationPlatform get() {
        return sPlatform;
    }


    @NonNull abstract LocationFactory getFactory();


    private static LocationPlatform findPlatform(@NonNull Context context) {
        LocationPlatform google = GoogleLocationPlatform.buildIfSupported(context);
        if (google != null) {
            return google;
        }

        LocationPlatform huawei = HuaweiLocationPlatform.buildIfSupported(context);
        if (huawei != null) {
            return huawei;
        }

        LocationPlatform lost = LostLocationPlatform.buildIfSupported(context);
        if (lost != null) {
            return lost;
        }

        throw new IllegalStateException(
                "Can't find supported platform, make sure to include one of the next artifacts:"
                        + " ':location-google', ':location-lost' or ':location-huawei'");
    }


    private static final class GoogleLocationPlatform extends LocationPlatform {
        private static final String LIBRARY_PACKAGE_NAME =
                "me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google";

        private static LocationFactory sFactory;

        @Override
        @NonNull LocationFactory getFactory() {
            return sFactory;
        }


        @Nullable static GoogleLocationPlatform buildIfSupported(@NonNull Context context) {
            try {
                sFactory = Objects.requireNonNull(
                        (LocationFactory) Class
                                .forName(LIBRARY_PACKAGE_NAME + ".GoogleLocationFactory")
                                .getMethod("buildIfSupported", Context.class)
                                .invoke(null, context)
                );
                Log.d(TAG, "GoogleLocationPlatform built!");
                return new GoogleLocationPlatform();
            } catch (Exception e) {
                //Ignore if unsupported
                Log.w(TAG, "Cannot build GoogleLocationPlatform. It is unsupported");
                return null;
            }
        }
    }

    private static final class LostLocationPlatform extends LocationPlatform {
        private static final String LIBRARY_PACKAGE_NAME =
                "com.tavorlabs.android.libraries.kits.location.internal.lost";

        private static LocationFactory sFactory;

        @Override
        @NonNull LocationFactory getFactory() {
            return sFactory;
        }


        @Nullable static LostLocationPlatform buildIfSupported(@NonNull Context context) {
            try {
                sFactory = Objects.requireNonNull(
                        (LocationFactory) Class
                                .forName(LIBRARY_PACKAGE_NAME + ".LostLocationFactory")
                                .getMethod("buildIfSupported", Context.class)
                                .invoke(null, context)
                );
                Log.d(TAG, "LostLocationPlatform built!");
                return new LostLocationPlatform();
            } catch (Exception e) {
                //Ignore if unsupported (for 'lost' this should be really weird)
                Log.w(TAG, "Cannot build LostLocationPlatform. It is unsupported");
                return null;
            }
        }
    }

    private static final class HuaweiLocationPlatform extends LocationPlatform {
        private static final String LIBRARY_PACKAGE_NAME =
                "me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei";

        private static LocationFactory sFactory;

        @Override
        @NonNull LocationFactory getFactory() {
            return sFactory;
        }


        @Nullable static HuaweiLocationPlatform buildIfSupported(@NonNull Context context) {
            try {
                sFactory = Objects.requireNonNull(
                        (LocationFactory) Class
                                .forName(LIBRARY_PACKAGE_NAME + ".HuaweiLocationFactory")
                                .getMethod("buildIfSupported", Context.class)
                                .invoke(null, context)
                );
                Log.d(TAG, "HuaweiLocationPlatform built!");
                return new HuaweiLocationPlatform();
            } catch (Exception e) {
                //Ignore if unsupported
                Log.w(TAG, "Cannot build HuaweiLocationPlatform. It is unsupported");
                return null;
            }
        }
    }

}
