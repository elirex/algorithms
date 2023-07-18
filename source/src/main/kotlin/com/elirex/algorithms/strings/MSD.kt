package com.elirex.algorithms.strings

import com.elirex.algorithms.sorting.Insertion
import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap
import kotlin.math.min

object MSD {

    private const val CUTOFF = 15
    private const val R = 256 // extended ASCII alphabet size

    fun sort(array: Array<String>) {
        val n = array.size
        val aux = Array<String>(n) { "" }
        sort(array, 0, n - 1, 0, aux)
    }

    private fun sort(
        array: Array<String>,
        low: Int,
        high: Int,
        d: Int,
        aux: Array<String>,
    ) {
        // cutoff to insertion sort for small sub-arrays
        if (high <= low + CUTOFF)  {
            insertion(array, low, high, d)
            // Insertion.sort(array, low, high, comparator = { v: String, w: String ->
            //     for (i in d until Math.min(v.length, w.length)) {
            //         when {
            //             v[i] < w[i] -> return@sort -1
            //             v[i] > w[i] -> return@sort 1
            //             else -> continue
            //         }
            //     }
            //     when {
            //         v.length < w.length -> -1
            //         else -> 1
            //     }
            // })
            return
        }

        // count frequency
        val count = Array<Int>(R + 2) { 0 }
        for (i in low .. high) {
           val index = array[i][d].code + 2
           count[index]++
        }

        // transform counts to indices
        for (r in 0 until R + 1) {
            count[r + 1] += count[r]
        }

        // distribute

        for (i in low .. high) {
            val c = array[i][d].code
            aux[count[c + 1]++] = array[i]
        }

        // copy back
        for (i in low .. high) {
            array[i] = aux[i - low]
        }

        // recursively sort for each character (excludes sentinel - 1)
        for (r in 0 until R) {
            sort(array, low + count[r], low + count[r + 1] - 1, d + 1, aux)
        }
    }

    private fun insertion(array: Array<String>, low: Int, high: Int, d: Int) {
        for (i in low .. high) {
            var j = i
            while (j > low && less(array[j], array[j - 1], d)) {
                swap(array, j, j - 1)
                j--
            }
        }
    }

    private fun less(v: String, w: String, d: Int): Boolean {
        for (i in d until min(v.length, w.length)) {
            when {
                v[i] < w[i] -> return true
                v[i] > w[i] -> return false
                else -> continue
            }
        }
        return v.length < w.length
    }

}