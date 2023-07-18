package com.elirex.algorithms.strings

object LSD {

    fun sort(array: Array<String>, w: Int) {
        val n = array.size
        val R = 256 // extend ASCII alphabet size

        val aux: Array<String> = Array(n) { "" }

        for (d in w -1 downTo 0) {
            val count = Array<Int>(R + 1) { 0 }

            // count frequency
            for (i in 0 until n) {
                val index = array[i][d].code + 1
                count[index]++
            }

            // compute cumulates
            for (r in 0 until R) {
               count[r+1] += count[r]
            }

            // move data
            for (i in 0 until n) {
               aux[count[array[i][d].code]++]  = array[i]
            }

            // copy back
            for (i in 0 until n) {
                array[i] = aux[i]
            }
        }
    }
}