package com.example.grand

import org.junit.Test

import org.junit.Assert.*

class WebhookTest {
    val mWebhook = Webhook()

    @Test
    fun open() {
        assertEquals("https://", mWebhook.call())
    }
}
