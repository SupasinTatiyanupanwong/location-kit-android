package me.tatiyanupanwong.supasin.android.libraries.kits.location.internal.interceptors;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import me.tatiyanupanwong.supasin.android.libraries.kits.tasks.internal.ResultInterceptor;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

/**
 * @since 1.0.0
 */
@RestrictTo(LIBRARY)
public final class VoidResultInterceptor extends ResultInterceptor<Void, Void> {

    public static final VoidResultInterceptor INSTANCE = new VoidResultInterceptor();

    private VoidResultInterceptor() {}

    @Override
    public @Nullable Void intercept(@Nullable Void ignored) {
        return null;
    }

}
