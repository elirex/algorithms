package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.shuffle
import com.elirex.algorithms.utils.swap

object Quick {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        shuffle(array)
        sort(array, 0, array.size - 1, comparator)
    }

    private fun <T: Comparable<T>> sort(
       array: Array<T>,
       lo: Int,
       hi: Int,
       comparator: Comparator<T>?
    ) {
        if (lo >= hi) {
            return
        }
        val j = partition(array, lo, hi, comparator)
        sort(array, lo, j - 1, comparator)
        sort(array, j + 1, hi, comparator)
    }

    fun <T: Comparable<T>> select(
        array: Array<T>,
        k: Int,
        comparator: Comparator<T>? = null
    ): T {
        if (k < 0 || k >= array.size) {
            throw IllegalArgumentException("The k is not between 0 and ${array.size}")
        }
        array.shuffle()
        var lo = 0
        var hi = array.size - 1
        while (lo < hi) {
            val v = partition(array, lo, hi, comparator)
            when {
                v > k -> hi = v - 1
                v < k -> lo = v + 1
                else -> return array[v];
            }
        }
        return array[lo]
    }

    private fun <T: Comparable<T>> partition(
        array: Array<T>,
        lo: Int,
        hi: Int,
        comparator: Comparator<T>?
    ): Int {
        var i = lo
        var j = hi + 1
        val v = array[lo]

        while (true) {
            // from the left end of the array to find an entry
            // that is >= the partitioning item.
            while (less(array[++i], v, comparator)) {
                if (i == hi) {
                    break
                }
            }

            // from the right end of the array to find an entry
            // that is <= the partitioning item.
            while (less(v, array[--j], comparator)) {
                if (j == lo) {
                    break
                }
            }

            // check if indices cross
            if (i >= j) {
                break
            }
            swap(array, i, j)
        }

        swap(array, lo, j)
        // array[lo .. j-1] <= array[j] <= array[j+1 .. hi]

        return j
    }
}