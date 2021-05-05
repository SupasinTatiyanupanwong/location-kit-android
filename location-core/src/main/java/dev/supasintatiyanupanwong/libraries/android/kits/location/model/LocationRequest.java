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

package dev.supasintatiyanupanwong.libraries.android.kits.location.model;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

/**
 * A data object that contains quality of service parameters for requests to the {@link
 * FusedLocationProviderClient}.
 * <p>
 * LocationRequest objects are used to request a quality of service for location updates from the
 * {@link FusedLocationProviderClient}.
 * <p>
 * For example, if your application wants high accuracy location it should create a location
 * request with {@link #setPriority(int)} set to {@link #PRIORITY_HIGH_ACCURACY} and {@link
 * #setInterval(long)} to 5 seconds. This would be appropriate for mapping applications that are
 * showing your location in real-time.
 * <p>
 * At the other extreme, if you want negligible power impact, but to still receive location updates
 * when available, then create a location request with {@link #setPriority(int)} set to {@link
 * #PRIORITY_NO_POWER}. With this request your application will not trigger (and therefore will not
 * receive any power blame) any location updates, but will receive locations triggered by other
 * applications. This would be appropriate for applications that have no firm requirement for
 * location, but can take advantage when available.
 * <p>
 * In between these two extremes is a very common use-case, where applications definitely want to
 * receive updates at a specified interval, and can receive them faster when available, but still
 * want a low power impact. These applications should consider {@link
 * #PRIORITY_BALANCED_POWER_ACCURACY} combined with a faster {@link #setFastestInterval(long)}
 * (such as 1 minute) and a slower {@link #setInterval(long)} (such as 60 minutes). They will only
 * be assigned power blame for the interval set by {@link #setInterval(long)}, but can still receive
 * locations triggered by other applications at a rate up to {@link #setFastestInterval(long)}.
 * This style of request is appropriate for many location aware applications, including background
 * usage. Do be careful to also throttle {@link #setFastestInterval(long)} if you perform heavy-
 * weight work after receiving an update - such as using the network.
 * <p>
 * Activities should strongly consider removing all location request when entering the background
 * (for example at {@code onPause()}), or at least swap the request to a larger interval and lower
 * quality.
 * <p>
 * Applications cannot specify the exact location sources, such as GPS, that are used by the
 * LocationClient. In fact, the system may have multiple location sources (providers) running and
 * may fuse the results from several sources into a single Location object.
 * <p>
 * Location requests from applications with {@code ACCESS_COARSE_LOCATION} and not {@code
 * ACCESS_FINE_LOCATION} will be automatically throttled to a slower interval, and the location
 * object will be obfuscated to only show a coarse level of accuracy.
 * <p>
 * All location requests are considered hints, and you may receive locations that are more/less
 * accurate, and faster/slower than requested.
 *
 * @since 1.0.0
 */
public interface LocationRequest {

    /**
     * Used with {@link #setPriority(int)} to request the most accurate locations available.
     *
     * @since 1.0.0
     */
    int PRIORITY_HIGH_ACCURACY = 100;

    /**
     * Used with {@link #setPriority(int)} to request "block" level accuracy.
     *
     * @since 1.0.0
     */
    int PRIORITY_BALANCED_POWER_ACCURACY = 102;

    /**
     * Used with {@link #setPriority(int)} to request "city" level accuracy.
     *
     * @since 1.0.0
     */
    int PRIORITY_LOW_POWER = 104;

    /**
     * Used with {@link #setPriority(int)} to request the best accuracy possible with zero
     * additional power consumption.
     *
     * @since 1.0.0
     */
    int PRIORITY_NO_POWER = 105;


    /**
     * Set the priority of the request.
     * <p>
     * Use with a priority constant such as {@link #PRIORITY_HIGH_ACCURACY}. No other values are
     * accepted.
     * <p>
     * The priority of the request is a strong hint to the {@link FusedLocationProviderClient} for
     * which location sources to use. For example, {@link #PRIORITY_HIGH_ACCURACY} is more likely
     * to use GPS, and PRIORITY_BALANCED_POWER_ACCURACY is more likely to use WIFI &amp; Cell tower
     * positioning, but it also depends on many other factors (such as which sources are available)
     * and is implementation dependent.
     * <p>
     * {@link #setPriority(int)} and {@link #setInterval(long)} are the most important parameters
     * on a location request.
     *
     * @param priority an accuracy or power constant
     * @throws IllegalArgumentException if the quality constant is not valid
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setPriority(int priority);

    /**
     * Set the desired interval for active location updates, in milliseconds.
     * <p>
     * The location client will actively try to obtain location updates for your application at
     * this interval, so it has a direct influence on the amount of power used by your application.
     * Choose your interval wisely.
     * <p>
     * This interval is inexact. You may not receive updates at all (if no location sources are
     * available), or you may receive them slower than requested. You may also receive them faster
     * than requested (if other applications are requesting location at a faster interval). The
     * fastest rate that that you will receive updates can be controlled with {@link
     * #setFastestInterval(long)}. By default this fastest rate is 6x the interval frequency.
     * <p>
     * Applications with only the coarse location permission may have their interval silently
     * throttled.
     * <p>
     * An interval of 0 is allowed, but not recommended, since location updates may be extremely
     * fast on future implementations.
     * <p>
     * {@link #setPriority(int)} and {@link #setInterval(long)} are the most important parameters
     * on a location request.
     *
     * @param millis desired interval in millisecond, inexact
     * @throws IllegalArgumentException if the interval is less than zero
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setInterval(long millis);

    /**
     * Explicitly set the fastest interval for location updates, in milliseconds.
     * <p>
     * This controls the fastest rate at which your application will receive location updates,
     * which might be faster than {@link #setInterval(long)} in some situations (for example, if
     * other applications are triggering location updates).
     * <p>
     * This allows your application to passively acquire locations at a rate faster than it
     * actively acquires locations, saving power.
     * <p>
     * Unlike {@link #setInterval(long)}, this parameter is exact. Your application will never
     * receive updates faster than this value.
     * <p>
     * If you don't call this method, a fastest interval will be selected for you. It will be a
     * value faster than your active interval ({@link #setInterval(long)}).
     * <p>
     * An interval of 0 is allowed, but not recommended, since location updates may be extremely
     * fast on future implementations.
     * <p>
     * If setFastestInterval(long) is set slower than {@link #setInterval(long)}, then your
     * effective fastest interval is setInterval(long).
     *
     * @param millis fastest interval for updates in milliseconds, exact
     * @throws IllegalArgumentException if the interval is less than zero
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setFastestInterval(long millis);

    /**
     * Sets the maximum wait time in milliseconds for location updates.
     * <p>
     * If you pass a value at least 2x larger than the interval specified with {@link
     * #setInterval(long)}, then location delivery may be delayed and multiple locations can be
     * delivered at once. Locations are determined at the {@link #setInterval(long)} rate, but can
     * be delivered in batch after the interval you set in this method. This can consume less
     * battery and give more accurate locations, depending on the device's hardware capabilities.
     * You should set this value to be as large as possible for your needs if you don't need
     * immediate location delivery.
     *
     * @param millis desired maximum wait time in millisecond, inexact
     * @throws IllegalArgumentException if the interval is less than zero
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setMaxWaitTime(long millis);

    /**
     * Set the duration of this request, in milliseconds.
     * <p>
     * The duration begins immediately (and not when the request is passed to the location client),
     * so call this method again if the request is re-used at a later time.
     * <p>
     * The location client will automatically stop updates after the request expires.
     * <p>
     * The duration includes suspend time. Values less than 0 are allowed, but indicate that the
     * request has already expired.
     *
     * @param millis duration of request in milliseconds
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setExpirationDuration(long millis);

    /**
     * Set the request expiration time, in millisecond since boot.
     * <p>
     * This expiration time uses the same time base as {@code SystemClock#elapsedRealtime()}.
     * <p>
     * The location client will automatically stop updates after the request expires.
     * <p>
     * The duration includes suspend time. Values before {@code SystemClock#elapsedRealtime()} are
     * allowed, but indicate that the request has already expired.
     *
     * @param millis expiration time of request, in milliseconds since boot including suspend
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setExpirationTime(long millis);

    /**
     * Set the number of location updates.
     * <p>
     * By default locations are continuously updated until the request is explicitly removed,
     * however you can optionally request a set number of updates. For example, if your application
     * only needs a single fresh location, then call this method with a value of 1 before passing
     * the request to the location client.
     * <p>
     * When using this option care must be taken to either explicitly remove the request when no
     * longer needed or to set an expiration with ({@link #setExpirationDuration(long)} or {@link
     * #setExpirationTime(long)}. Otherwise in some cases if a location can't be computed, this
     * request could stay active indefinitely consuming power.
     *
     * @param numUpdates the number of location updates requested
     * @throws IllegalArgumentException if {@code numUpdates} is 0 or less
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setNumUpdates(int numUpdates);

    /**
     * Set the minimum displacement between location updates in meters.
     * <p>
     * By default this is 0.
     *
     * @param smallestDisplacementMeters the smallest displacement in meters the user must move
     * between location updates.
     * @throws IllegalArgumentException if {@code smallestDisplacementMeters} is negative
     * @return the same object, so that setters can be chained
     * @since 1.0.0
     */
    @NonNull LocationRequest setSmallestDisplacement(
            @FloatRange(from = 0) float smallestDisplacementMeters);

}
