package com.example.grand

import android.R.string.no
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL
import android.R.string.no
import java.io.BufferedReader


class Sesame{
    fun open() : String {
        val url = URL("https://api.candyhouse.co/public/sesame/" + System.getenv("SESAME_DEVICE_ID"))
        val conn = url.openConnection() as HttpURLConnection
        conn.doOutput = true
        conn.setChunkedStreamingMode(0)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Content-Type", "application/json")
        val authToken = System.getenv("SESAME_AUTH_TOKEN")
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