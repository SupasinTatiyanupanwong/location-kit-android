package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.exception;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationApiException;

public final class GoogleLocationApiException extends Exception implements LocationApiException {

    private final com.google.android.gms.common.api.ApiException mDelegate;

    public GoogleLocationApiException(com.google.android.gms.common.api.ApiException delegate) {
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

        GoogleLocationApiException that = (GoogleLocationApiException) obj;

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


    public static @NonNull com.google.android.gms.common.api.ApiException unwrap(
            @NonNull LocationApiException wrapped) {
        return ((GoogleLocationApiException) wrapped).mDelegate;
    }

    public static @NonNull LocationApiException wrap(
            @NonNull com.google.android.gms.common.api.ApiException delegate) {
        return new GoogleLocationApiException(delegate);
    }
}
