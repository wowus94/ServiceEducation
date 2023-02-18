package com.vlyashuk.serviceeducation

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log


class MyIntentServiceTwo : IntentService(NAME_SERVICE) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true)
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        val page = intent?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 10) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }


    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentService: $message")
    }

    companion object {

        private const val NAME_SERVICE = "MyIntentService"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServiceTwo::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}