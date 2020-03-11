package com.example.grand

import org.junit.Test

import org.junit.Assert.*

class SesameTest {
    val mSesame = Sesame()

    @Test
    fun open() {
        assertEquals("https://", mSesame.open())
    }
}
