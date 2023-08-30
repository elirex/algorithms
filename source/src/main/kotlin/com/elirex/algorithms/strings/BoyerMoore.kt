package com.elirex.algorithms.strings

import kotlin.math.max

class BoyerMoore(
    private val pattern: String,
    R: Int = 256,
) {
    private val m: Int
    private val right: Array<Int>

    init {
        m = pattern.length
        right = Array(R) { -1 }
        pattern.forEachIndexed { j, c ->
            right[pattern[j].code] = j
        }
    }

    fun search(text: String): Int {
        val n = text.length
        var skip = 0
        var i = 0
        while (i <= (n - m)) {
            skip = 0
            for (j in m - 1 downTo 0) {
                if (pattern[j] != text[i + j]) {
                    skip = max(1, j - right[text[i + j].code])
                    break
                }
            }
            if (skip == 0) {
                return i
            }
            i += skip
        }
        return n
    }
}