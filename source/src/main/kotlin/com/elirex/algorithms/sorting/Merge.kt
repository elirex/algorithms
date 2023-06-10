package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less

object Merge {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val extraArray: Array<T> = array.clone()
        sort(extraArray, array, 0, array.size - 1, comparator)
    }

    private fun <T: Comparable<T>> sort(
        source: Array<T>,
        destination: Array<T>,
        lo: Int,
        hi: Int,
        comparator: Comparator<T>? = null,
    ) {

        if (hi <= lo + CUTOFF) {
            Insertion.sort(destination, comparator)
            return
        }

        val mid = lo + (hi - lo) / 2
        sort(destination, source, lo, mid)
        sort(destination, source, mid + 1, hi)

        if (!less(source[mid + 1], source[mid], comparator)) {
            System.arraycopy(source, lo, destination, lo, hi - lo + 1)
            return
        }
    }

    private fun <T: Comparable<T>> merge(
        source: Array<T>,
        destination: Array<T>,
        lo: Int,
        mid: Int,
        hi: Int,
        comparator: Comparator<T>? = null
    ) {
        var i = lo
        var j = mid + 1
        for (k in lo..hi) {
            destination[k] = when {
                i > mid -> source[j++]
                j > hi -> source[i++]
                less(source[j], source[i], comparator) -> source[j++]
                else -> source[i++]
            }
        }
    }

    private const val CUTOFF: Int = 7
}