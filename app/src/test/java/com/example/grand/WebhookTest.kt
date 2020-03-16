package com.example.grand

import org.junit.Test

import org.junit.Assert.*

class WebhookTest {
    val mWebhook = Webhook()

    @Test
    fun call() {
        assertEquals("{\"task_id\":", mWebhook.call().substring(0, 11))
    }
}
