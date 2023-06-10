package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less

object MergeTopDown {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val extraArray = array.clone()
        sort(array, extraArray, 0, array.size - 1, comparator)
    }

    private fun <T: Comparable<T>> sort(array: Array<T>,
                                        extraArray: Array<T>,
                                        lo: Int,
                                        hi: Int,
                                        comparator: Comparator<T>? = null) {
        if (lo >= hi) {
            return
        }
        val mid = lo + (hi - lo) / 2
        sort(array, extraArray, lo, mid, comparator)
        sort(array, extraArray, mid + 1, hi, comparator)
        merge(array, extraArray, lo, mid, hi, comparator)
    }

    private fun <T: Comparable<T>> merge(array: Array<T>,
                                         extraArray: Array<T>,
                                         lo: Int,
                                         mid: Int,
                                         hi: Int,
                                         comparator: Comparator<T>? = null) {
        for (i in lo..hi) {
            extraArray[i] = array[i]
        }
        var i: Int = lo
        var j: Int = mid + 1
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