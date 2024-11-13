package com.plcoding.run.network

import com.plcoding.core.domain.location.Location
import com.plcoding.core.domain.run.Run
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

fun RunDto.toRun(): Run {
    return Run(
        id = id,
        dateTimeUTC = Instant.parse(dateTimeUtc).atZone(ZoneId.of("UTC")),
        duration = durationMillis.milliseconds,
        distanceInMeters = distanceMeters,
        location = Location(
            lat = lat,
            long = long
        ),
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        mapPictureUrl = mapPictureUrl
    )
}

fun Run.toCreateRunRequest(): CreateRunRequest {
    return CreateRunRequest(
        id = id!!,
        durationMillis = duration.inWholeMilliseconds,
        distanceMeters = distanceInMeters,
        lat = location.lat,
        long = location.long,
        avgSpeedKmh = avgSpeedKmh,
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        epochMillis = dateTimeUTC.toEpochSecond() * 1000L
    )
}