package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.shuffle
import com.elirex.algorithms.utils.swap

object Quick3way {

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

        var lt = lo
        var gt = hi
        val v = array[lo]

        var i = lo + 1
        while (i <= gt) {
            val cmp: Int = comparator?.compare(array[i], v) ?: array[i].compareTo(v)
            when {
                cmp < 0 -> swap(array, lt++, i++)
                cmp > 0 -> swap(array, i, gt--)
                else -> i++
            }
        }
        sort(array, lo, lt - 1, comparator)
        sort(array, gt + 1, hi, comparator)
    }
}