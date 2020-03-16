package com.example.grand

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
        var output = conn.getOutputStream()
        val body = "{\"command\":\"unlock\"}" as java.lang.String
        output.write(body.getBytes())
        output.flush()
        output.close()
        val input = conn.getInputStream()
        input.read()
        input.close()
        return "https://"
    }
}