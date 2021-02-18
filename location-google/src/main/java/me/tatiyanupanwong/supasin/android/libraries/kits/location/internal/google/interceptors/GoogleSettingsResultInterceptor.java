package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.interceptors;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.google.model.GoogleLocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.location.model.LocationSettingsResponse;
import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.internal.ResultInterceptor;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

/**
 * @since 1.1.0
 */
@RestrictTo(LIBRARY)
public final class GoogleSettingsResultInterceptor extends ResultInterceptor<com.google.android.gms.location.LocationSettingsResponse, LocationSettingsResponse> {

    public static final GoogleSettingsResultInterceptor INSTANCE = new GoogleSettingsResultInterceptor();

    private GoogleSettingsResultInterceptor() {}

    @Override
    public @Nullable LocationSettingsResponse intercept(@Nullable com.google.android.gms.location.LocationSettingsResponse response) {
        if (response != null) {
            return GoogleLocationSettingsResponse.wrap(response);
        } else {
            return null;
        }
    }

}
