/*
 * Copyright 2017 the original author or authors.
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
package io.lettuce.core;

import io.lettuce.core.*;
import java.util.List;
import java.util.Set;

/**
 * ${intent} for the Geo-API.
 *
 * @author Mark Paluch
 * @since 4.0
 */
public interface RedisGeoCommands<K, V> {

    /**
     * Single geo add.
     *
     * @param key the key of the geo set
     * @param longitude the longitude coordinate according to WGS84
     * @param latitude the latitude coordinate according to WGS84
     * @param member the member to add
     * @return Long integer-reply the number of elements that were added to the set
     */
    Long geoadd(K key, double longitude, double latitude, V member);

    /**
     * Multi geo add.
     *
     * @param key the key of the geo set
     * @param lngLatMember triplets of double longitude, double latitude and V member
     * @return Long integer-reply the number of elements that were added to the set
     */
    Long geoadd(K key, Object... lngLatMember);

    /**
     * Retrieve Geohash strings representing the position of one or more elements in a sorted set value representing a geospatial index.
     *
     * @param key the key of the geo set
     * @param members the members
     * @return bulk reply Geohash strings in the order of {@code members}. Returns {@literal null} if a member is not found.
     */
    List<Value<String>> geohash(K key, V... members);

    /**
     * Retrieve members selected by distance with the center of {@code longitude} and {@code latitude}.
     *
     * @param key the key of the geo set
     * @param longitude the longitude coordinate according to WGS84
     * @param latitude the latitude coordinate according to WGS84
     * @param distance radius distance
     * @param unit distance unit
     * @return bulk reply
     */
    Set<V> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit);

    /**
     * Retrieve members selected by distance with the center of {@code longitude} and {@code latitude}.
     *
     * @param key the key of the geo set
     * @param longitude the longitude coordinate according to WGS84
     * @param latitude the latitude coordinate according to WGS84
     * @param distance radius distance
     * @param unit distance unit
     * @param geoArgs args to control the result
     * @return nested multi-bulk reply. The {@link GeoWithin} contains only fields which were requested by {@link GeoArgs}
     */
    List<GeoWithin<V>> georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoArgs geoArgs);

    /**
     * Perform a {@link #georadius(Object, double, double, double, GeoArgs.Unit, GeoArgs)} query and store the results in a sorted set.
     *
     * @param key the key of the geo set
     * @param longitude the longitude coordinate according to WGS84
     * @param latitude the latitude coordinate according to WGS84
     * @param distance radius distance
     * @param unit distance unit
     * @param geoRadiusStoreArgs args to store either the resulting elements with their distance or the resulting elements with
     *        their locations a sorted set.
     * @return Long integer-reply the number of elements in the result
     */
    Long georadius(K key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs);

    /**
     * Retrieve members selected by distance with the center of {@code member}. The member itself is always contained in the
     * results.
     *
     * @param key the key of the geo set
     * @param member reference member
     * @param distance radius distance
     * @param unit distance unit
     * @return set of members
     */
    Set<V> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit);

    /**
     *
     * Retrieve members selected by distance with the center of {@code member}. The member itself is always contained in the
     * results.
     *
     * @param key the key of the geo set
     * @param member reference member
     * @param distance radius distance
     * @param unit distance unit
     * @param geoArgs args to control the result
     * @return nested multi-bulk reply. The {@link GeoWithin} contains only fields which were requested by {@link GeoArgs}
     */
    List<GeoWithin<V>> georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoArgs geoArgs);

    /**
     * Perform a {@link #georadiusbymember(Object, Object, double, GeoArgs.Unit, GeoArgs)} query and store the results in a sorted set.
     *
     * @param key the key of the geo set
     * @param member reference member
     * @param distance radius distance
     * @param unit distance unit
     * @param geoRadiusStoreArgs args to store either the resulting elements with their distance or the resulting elements with
     *        their locations a sorted set.
     * @return Long integer-reply the number of elements in the result
     */
    Long georadiusbymember(K key, V member, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs<K> geoRadiusStoreArgs);

    /**
     * Get geo coordinates for the {@code members}.
     *
     * @param key the key of the geo set
     * @param members the members
     *
     * @return a list of {@link GeoCoordinates}s representing the x,y position of each element specified in the arguments. For
     *         missing elements {@literal null} is returned.
     */
    List<GeoCoordinates> geopos(K key, V... members);

    /**
     *
     * Retrieve distance between points {@code from} and {@code to}. If one or more elements are missing {@literal null} is
     * returned. Default in meters by, otherwise according to {@code unit}
     *
     * @param key the key of the geo set
     * @param from from member
     * @param to to member
     * @param unit distance unit
     *
     * @return distance between points {@code from} and {@code to}. If one or more elements are missing {@literal null} is
     *         returned.
     */
    Double geodist(K key, V from, V to, GeoArgs.Unit unit);

}