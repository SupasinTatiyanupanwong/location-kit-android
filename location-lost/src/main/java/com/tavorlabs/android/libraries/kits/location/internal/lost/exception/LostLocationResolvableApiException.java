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

package com.tavorlabs.android.libraries.kits.location.internal.lost.exception;

import android.app.PendingIntent;

import java.util.Objects;

import androidx.annotation.NonNull;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationApiResolvableException;

public final class LostLocationResolvableApiException extends LostLocationApiException implements LocationApiResolvableException {

    private final PendingIntent pendingIntent;

    public LostLocationResolvableApiException(
            @NonNull com.mapzen.android.lost.api.LocationSettingsResult delegate) {
        super(delegate);
        this.pendingIntent = delegate.getStatus().getResolution();
    }

    @NonNull
    @Override
    public PendingIntent getResolution() {
        return this.pendingIntent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LostLocationResolvableApiException that = (LostLocationResolvableApiException) o;
        return pendingIntent.equals(that.pendingIntent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pendingIntent);
    }

}
