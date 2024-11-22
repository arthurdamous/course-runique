package com.plcoding.analytics.data

import com.plcoding.analytics.domain.AnalyticsValue
import com.plcoding.analytics.domain.AnalyticsRepository
import com.plcoding.core.database.dao.AnalyticsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class RoomAnalyticsRepository(
    private val dao: AnalyticsDao
) : AnalyticsRepository{


    override suspend fun getAnalyticsValues(): AnalyticsValue {
        return withContext(Dispatchers.IO) {
            val totalDistance = async { dao.getTotalDistance() }
            val totalTimeMillis = async { dao.getTotalTimeRun() }
            val maxRunSpeed = async { dao.getMaxRunSpeed() }
            val avgDistancePerRun = async { dao.getAvgDistancePerRun() }
            val avgPacePerRun = async { dao.getAvgPacePerRun() }

            AnalyticsValue(
                totalDistanceRun = totalDistance.await(),
                totalTimeRun = totalTimeMillis.await().milliseconds,
                fastestEverRun = maxRunSpeed.await(),
                avgDistancePerRun = avgDistancePerRun.await(),
                avgPacePerRun = avgPacePerRun.await()
            )
        }
    }


}