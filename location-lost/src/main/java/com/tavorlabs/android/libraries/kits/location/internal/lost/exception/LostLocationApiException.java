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

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationApiException;

public class LostLocationApiException extends Exception implements LocationApiException {

    protected final int statusCode;
    protected final String message;

    public LostLocationApiException(
            @NonNull com.mapzen.android.lost.api.LocationSettingsResult delegate) {
        this.statusCode = delegate.getStatus().getStatusCode();
        this.message = delegate.getStatus().getStatusMessage();
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Nullable
    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LostLocationApiException that = (LostLocationApiException) o;
        return statusCode == that.statusCode &&
                message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message);
    }

}
