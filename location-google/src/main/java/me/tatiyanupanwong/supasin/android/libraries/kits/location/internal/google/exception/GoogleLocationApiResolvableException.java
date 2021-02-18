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
