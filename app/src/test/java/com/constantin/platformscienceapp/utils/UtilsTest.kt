package com.constantin.platformscienceapp.utils

import org.junit.Assert.*

import org.junit.Test

class UtilsTest {

    @Test
    fun `count number of consonants in a string`() {
        val count: Int = count("It is a good day today", false)
        assertEquals(count, 9)
    }

    @Test
    fun `count number of vowels in a string`() {
        val count: Int = count("It is a good day today")
        assertEquals(count, 8)
    }

    @Test
    fun `two numbers have common factors`() {
        var hasCommonFactors = hasCommonFactors(3, 12)
        assertTrue(hasCommonFactors)

        hasCommonFactors = hasCommonFactors(5, 18)
        assertFalse(hasCommonFactors)
    }
}
