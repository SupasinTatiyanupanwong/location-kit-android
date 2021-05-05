/*
 * Copyright 2020 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.supasintatiyanupanwong.libraries.android.kits.location.internal.interceptors;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import dev.supasintatiyanupanwong.libraries.android.kits.tasks.internal.ResultInterceptor;

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
