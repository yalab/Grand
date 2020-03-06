package com.example.grand

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import com.example.grand.Sesame

class SesameTest {
    val mSesame = Sesame()

    @Test
    fun url() {
        assertEquals("https://", mSesame.url())
    }
}
