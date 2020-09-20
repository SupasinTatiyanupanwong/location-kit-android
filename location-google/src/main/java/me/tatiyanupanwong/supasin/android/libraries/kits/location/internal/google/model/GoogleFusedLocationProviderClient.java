package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.internal.google.tasks.GoogleTask;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.LocationResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors.VoidResultInterceptor;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.FusedLocationProviderClient;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationCallback;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationListener;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationRequest;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.Task;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public final class GoogleFusedLocationProviderClient implements FusedLocationProviderClient {

    private final com.google.android.gms.location.FusedLocationProviderClient mDelegate;

    private final GoogleLocationCallbacksHolder mLocationCallbacksHolder =
            new GoogleLocationCallbacksHolder();

    private final GoogleLocationListenersHolder mLocationListenersHolder =
            new GoogleLocationListenersHolder();

    public GoogleFusedLocationProviderClient(@NonNull Context context) {
        mDelegate = com.google.android.gms.location.LocationServices
                .getFusedLocationProviderClient(context);
    }

    public GoogleFusedLocationProviderClient(@NonNull Activity activity) {
        mDelegate = com.google.android.gms.location.LocationServices
                .getFusedLocationProviderClient(activity);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Location> getLastLocation() {
        return new GoogleTask<>(
                mDelegate.getLastLocation(),
                LocationResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationCallback callback) {
        return requestLocationUpdates(request, callback, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener) {
        return requestLocationUpdates(request, listener, null);
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            final @NonNull LocationCallback callback, @Nullable Looper looper) {
        return new GoogleTask<>(
                mDelegate.requestLocationUpdates(
                        GoogleLocationRequest.unwrap(request),
                        mLocationCallbacksHolder.put(callback),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    @RequiresPermission(anyOf = { ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION })
    public @NonNull Task<Void> requestLocationUpdates(@NonNull LocationRequest request,
            @NonNull LocationListener listener, @Nullable Looper looper) {
        return new GoogleTask<>(
                mDelegate.requestLocationUpdates(
                        GoogleLocationRequest.unwrap(request),
                        mLocationListenersHolder.put(listener),
                        looper),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationCallback callback) {
        return new GoogleTask<>(
                mDelegate.removeLocationUpdates(mLocationCallbacksHolder.remove(callback)),
                VoidResultInterceptor.INSTANCE
        );
    }

    @Override
    public @NonNull Task<Void> removeLocationUpdates(@NonNull LocationListener listener) {
        return new GoogleTask<>(
                mDelegate.removeLocationUpdates(mLocationListenersHolder.remove(listener)),
                VoidResultInterceptor.INSTANCE
        );
    }

}
