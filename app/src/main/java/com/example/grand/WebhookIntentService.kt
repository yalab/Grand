package com.example.grand

import android.app.IntentService
import android.content.Intent
import android.content.Context
import kotlinx.coroutines.*
import android.widget.Toast
import android.os.Looper
import android.os.Handler

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
            showToast("Call open")
            val responseBody = async { Webhook().call() }.await()
            showToast(responseBody)
        }
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post(Runnable {
            Toast.makeText(
                applicationContext,
                message,
                Toast.LENGTH_LONG
            ).show()
        })
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
