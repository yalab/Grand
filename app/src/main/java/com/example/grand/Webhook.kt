package com.example.grand

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class Webhook{
    fun call() : String {
        val url = URL("https://api.candyhouse.co/public/sesame/" + BuildConfig.SESAME_DEVICE_ID)
        val conn = url.openConnection() as HttpURLConnection
        conn.doOutput = true
        conn.setChunkedStreamingMode(0)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/json")
        val authToken = BuildConfig.SESAME_AUTH_TOKEN
        conn.setRequestProperty("Authorization", authToken)
        val params = "{\"command\":\"lock\"}" as java.lang.String
        var output = conn.getOutputStream()
        output.write(params.getBytes())
        output.flush()
        output.close()
        val input = BufferedInputStream(conn.getInputStream())
        var buf : ByteArray
        try {
            buf = input.readBytes()
        } finally {
            conn.disconnect()
        }
        return String(buf)
    }
}
