package com.example.grand

import android.app.IntentService
import android.content.Intent
import android.content.Context
import kotlinx.coroutines.*

private const val ACTION_OPEN = "com.example.grand.action.OPEN"

class WebhookIntentService : IntentService("WebhookIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_OPEN -> {
                handleActionOpen()
            }
        }
    }

    private fun handleActionOpen() {
        runBlocking {
            val ret = async { Webhook().call() }.await()
            println(ret)
        }
    }


    companion object {
        @JvmStatic
        fun startActionOpen(context: Context) {
            val intent = Intent(context, WebhookIntentService::class.java).apply {
                action = ACTION_OPEN
            }
            context.startService(intent)
        }
    }
}
