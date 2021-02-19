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

package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.huawei.exception;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationApiException;

public final class HuaweiLocationApiException extends Exception implements LocationApiException {

    private final com.huawei.hms.common.ApiException mDelegate;

    public HuaweiLocationApiException(com.huawei.hms.common.ApiException delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public int getStatusCode() {
        return mDelegate.getStatusCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HuaweiLocationApiException that = (HuaweiLocationApiException) obj;

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


    public static @NonNull com.huawei.hms.common.ApiException unwrap(
            @NonNull LocationApiException wrapped) {
        return ((HuaweiLocationApiException) wrapped).mDelegate;
    }

    public static @NonNull LocationApiException wrap(
            @NonNull com.huawei.hms.common.ApiException delegate) {
        return new HuaweiLocationApiException(delegate);
    }
}
