package com.plcoding.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.plcoding.core.domain.run.RunRepository
import com.plcoding.core.domain.util.DataError

class FetchRunsWorker(
    context: Context,
    params: WorkerParameters,
    private val runRepository: RunRepository,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        return when (val result = runRepository.fetchRuns()) {
            is com.plcoding.core.domain.util.Result.Error -> {
                when (result.error) {
                    DataError.Local.DISK_FULL -> Result.failure()
                    DataError.Network.REQUEST_TIMEOUT -> Result.retry()
                    DataError.Network.UNAUTHORIZED -> Result.retry()
                    DataError.Network.CONFLICT -> Result.retry()
                    DataError.Network.TOO_MANY_REQUESTS -> Result.retry()
                    DataError.Network.NO_INTERNET -> Result.retry()
                    DataError.Network.PAYLOAD_TOO_LARGE -> Result.failure()
                    DataError.Network.SERVER_ERROR -> Result.retry()
                    DataError.Network.SERIALIZATION -> Result.retry()
                    DataError.Network.UNKNOWN -> Result.failure()
                }
            }

            is com.plcoding.core.domain.util.Result.Success -> Result.success()
        }
    }

}