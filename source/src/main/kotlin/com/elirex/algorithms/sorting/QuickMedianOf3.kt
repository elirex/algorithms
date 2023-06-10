package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object QuickMedianOf3 {

    private const val INSERTION_SORT_CUTOFF: Int = 8

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        sort(array, 0, array.size - 1, comparator)
    }

    private fun <T: Comparable<T>> sort(array: Array<T>, lo: Int, hi: Int, comparator: Comparator<T>?) {
        if (lo >= hi) {
            return
        }

        val n = hi - lo + 1
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(array, lo, hi + 1, comparator)
            return
        }

        val j = partition(array, lo, hi, comparator)
        sort(array, lo, j - 1, comparator)
        sort(array, j + 1, hi, comparator)
    }

    private fun <T: Comparable<T>> partition(
        array: Array<T>,
        lo: Int,
        hi: Int,
        comparator: Comparator<T>?
    ): Int {
        val n = hi - lo + 1
        val m = median3(array, lo, lo + n / 2, hi, comparator)
        swap(array, m, lo)

        var i = lo
        var j = hi + 1
        val v = array[lo]

        while (less(array[++i], v, comparator)) {
            if (i == hi) {
                swap(array, lo, hi)
                return hi
            }
        }

        while (less(v, array[--j], comparator)) {
            if (j == lo + 1) {
                return lo
            }
        }

        while (i < j) {
            swap(array, i, j)
            while (less(array[++i], v, comparator));
            while (less(v, array[--j], comparator));
        }
        swap(array, lo, j)
        return j
    }

    private fun <T: Comparable<T>> median3(
        array: Array<T>,
        i: Int,
        j: Int,
        k: Int,
        comparator: Comparator<T>?
    ): Int {
        return if (less(array[i], array[j], comparator)) { // a[i] < a[j]
            if (less(array[j], array[k], comparator)) { // a[j] < a[k]
                j // means a[i] < a[j] < a[k]
            } else if (less(array[i], array[k])) { // a[i] < a[k]
                k // means a[i] < a[k] < a[j]
            } else {
                i // means a[k] < a[i] < a[j]
            }
        } else { // a[j] <= a[i]
           if (less(array[k], array[j], comparator)) { // a[k] < a[j]
               j  // means a[k] < a[j] < a[i]
           } else if (less(array[k], array[i], comparator)) { // a[k] < a[i]
               k // means a[j] < a[k] < a[i]
           } else {
               i // means a[j] < a[i] < a[k]
           }
        }

    }
}