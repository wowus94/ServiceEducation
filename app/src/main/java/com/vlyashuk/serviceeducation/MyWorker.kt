package com.vlyashuk.serviceeducation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }

        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyWorker: $message")
    }

    companion object {

        private const val PAGE = "page"

        const val WORK_NAME = "work name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().apply {
                setInputData(workDataOf(PAGE to page))
                setConstraints(makeConstrains())
            }
                .build()
        }

        private fun makeConstrains(): Constraints {
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }
}