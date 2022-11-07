package com.alexvlive.topone.util

import com.alexvlive.topone.common.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetResultWithRetryPolicy<T> (
    private var numRetries: Long = 2,
    private val delayMillis: Long = 300,
    private val delayFactor: Long = 2,
    private val lmd: suspend () -> T
) {

    suspend fun execute(): ResultResponse<T> {
        var delay = 0L
        while (true) {
            try {
                if (delay != 0L) {
                    withContext(Dispatchers.IO) {
                        Thread.sleep(delay)
                    }
                }
                return ResultResponse.Success(lmd())
            } catch (e: Exception) {
                if (delay == 0L) {
                    delay = delayMillis
                } else {
                    delay *= delayFactor
                }
                if (--numRetries < 0L) {
                    return ResultResponse.Error(exception = e)
                }
            }
        }
    }
}
