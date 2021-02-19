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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.exception;

import android.app.PendingIntent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationApiResolvableException;

public final class GoogleLocationApiResolvableException extends Exception implements LocationApiResolvableException {

    private final com.google.android.gms.common.api.ResolvableApiException mDelegate;

    public GoogleLocationApiResolvableException(com.google.android.gms.common.api.ResolvableApiException delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public int getStatusCode() {
        return mDelegate.getStatusCode();
    }

    @NonNull
    @Override
    public PendingIntent getResolution() {
        return mDelegate.getResolution();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GoogleLocationApiResolvableException that = (GoogleLocationApiResolvableException) obj;

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


    public static @NonNull com.google.android.gms.common.api.ResolvableApiException unwrap(
            @NonNull LocationApiResolvableException wrapped) {
        return ((GoogleLocationApiResolvableException) wrapped).mDelegate;
    }

    public static @NonNull LocationApiResolvableException wrap(
            @NonNull com.google.android.gms.common.api.ResolvableApiException delegate) {
        return new GoogleLocationApiResolvableException(delegate);
    }
}
