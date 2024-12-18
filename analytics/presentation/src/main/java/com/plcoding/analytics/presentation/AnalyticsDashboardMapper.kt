package com.plcoding.analytics.presentation

import com.plcoding.analytics.domain.AnalyticsValue
import com.plcoding.core.presentation.ui.formatted
import com.plcoding.core.presentation.ui.toFormattedKm
import com.plcoding.core.presentation.ui.toFormattedKmh
import com.plcoding.core.presentation.ui.toFormattedMeters
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

fun Duration.toFormattedTotalTime(): String {
    val days = toLong(DurationUnit.DAYS)
    val hours = toLong(DurationUnit.HOURS) % 24
    val minutes = toLong(DurationUnit.MINUTES) % 60

    return "${days}d ${hours}h ${minutes}min"
}

fun AnalyticsValue.toAnalyticsDashboardState(): AnalyticsDashboardState {
    return AnalyticsDashboardState(
        totalDistanceRun = (totalDistanceRun / 1000.0).toFormattedKm(),
        totalTimeRun = totalTimeRun.toFormattedTotalTime(),
        fastestEverRun = fastestEverRun.toFormattedKmh(),
        avgDistancePerRun = (avgDistancePerRun / 1000.0).toFormattedKm(),
        avgPaceRun = avgPacePerRun.seconds.formatted()
    )
}