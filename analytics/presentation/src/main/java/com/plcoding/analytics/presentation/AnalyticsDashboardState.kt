package com.plcoding.analytics.presentation

data class AnalyticsDashboardState(
    val totalDistanceRun: String = "",
    val totalTimeRun: String = "",
    val fastestEverRun: String = "",
    val avgDistancePerRun: String = "",
    val avgPaceRun: String = "",
)