package com.elirex.algorithms.strings

import com.elirex.algorithms.extensions.swap
import com.elirex.algorithms.utils.shuffle
import com.elirex.algorithms.utils.swap
import kotlin.math.min

object Quick3string {

    private const val CUTOFF = 15 // cutoff to insertion sort

    fun sort(array: Array<String>) {
        shuffle(array)
        sort(array, 0, array.size - 1, 0)
    }

    private fun sort(
        array: Array<String>,
        low: Int,
        high: Int,
        d: Int,
    ) {
        if (high <= low + CUTOFF) {
            insertion(array, low, high, d)
            return
        }

        var lt = low
        var gt = high
        val v = charAt(array[low], d)
        var i = low + 1
        while (i <= gt) {
            val t = charAt(array[i], d)
            when {
                t < v -> array.swap(lt++, i++)
                t > v -> array.swap(i, gt--)
                else -> i++
            }
        }
        sort(array, low, lt - 1, d)
        if (v >= 0) {
            sort(array, lt, gt, d + 1)
        }
        sort(array, gt + 1, high, d)
    }

    private fun charAt(s: String, d: Int): Int {
        return when {
            d == s.length -> -1
            else -> s[d].code
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