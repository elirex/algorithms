package com.elirex.algorithms.strings

class KMP(pattern: String, R: Int = 256) {

    private val m: Int // length of pattern
    private val dfa: Array<Array<Int>> // the KMP automaton

    init {
        m = pattern.length
        dfa = Array(R) { Array(m) { 0 } }
        dfa[pattern[0].code][0] = 1
        var x = 0
        for (j in 1 until m) {
            for (c in 0 until R) {
                dfa[c][j] = dfa[c][x] // copy mismatch cases.
            }
            dfa[pattern[j].code][j] = j + 1 // set match case.
            x = dfa[pattern[j].code][x] // update restart state.
        }
    }

    fun search(txt: String): Int {
        val n = txt.length
        var i = 0
        var j = 0
        while (i < n && j < m) {
            j = dfa[txt[i].code][j]
            i++
        }
        if (j == m) {
            return i - m// found
        }
        return n // not found
    }
}