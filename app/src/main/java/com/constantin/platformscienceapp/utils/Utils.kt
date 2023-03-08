package com.constantin.platformscienceapp.utils

import android.util.Log

/**
 * @return vowel or consonant count
 */
fun count(word: String, isVowels: Boolean = true): Int {
    var vowelsCount = 0
    var consonantsCount = 0
    val dest = word.lowercase()
    for (i in dest.indices) {
        when (dest[i]) {
            'a', 'e', 'i', 'o', 'u' -> vowelsCount++
            in 'a'..'z' -> consonantsCount++
        }
    }
    if (isVowels) {
        return vowelsCount
    }
    return consonantsCount
}

/**
 * @return true if numbers have common factors > 1
 */
fun hasCommonFactors(x: Int, y: Int): Boolean {
    var i = 2
    while (i <= x && i <= y) {
        if (x % i == 0 && y % i == 0) {
            return true
        }
        i++
    }
    return false
}
