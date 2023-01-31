package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import kotlin.math.min

object MergeBottomUp {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        val extraArray = array.clone()
        var len = 1
        while (len < n) {
            var lo = 0
            while (lo < (n - len)) {
                val mid = lo + len - 1
                val hi = min(lo + len + len - 1, n - 1)
                merge(array, extraArray, lo, mid, hi, comparator)
                lo += (len + len)
            }
            len *= 2
        }
    }

    private fun <T: Comparable<T>> merge(
        array: Array<T>,
        extraArray: Array<T>,
        lo: Int,
        mid: Int,
        hi: Int,
        comparator: Comparator<T>? = null
    ) {
        for (i in lo..hi) {
            extraArray[i] = array[i]
        }
        var i = lo
        var j = mid + 1
        for (k in lo..hi) {
            array[k] = when {
                i > mid -> extraArray[j++]
                j > hi -> extraArray[i++]
                less(extraArray[j], extraArray[i], comparator) -> extraArray[j++]
                else -> extraArray[i++]
            }
        }
    }
}