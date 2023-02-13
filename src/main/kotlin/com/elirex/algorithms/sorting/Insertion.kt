package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object Insertion {

    fun <T : Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        for (i in 1 until n) {
            var j = i
            while (j > 0 && less(array[j], array[j - 1], comparator)) {
                swap(array, j, j - 1)
                j--
            }
        }
    }

    fun <T : Comparable<T>> sort(array: Array<T>, lo: Int, hi: Int, comparator: Comparator<T>? = null) {
        for (i in lo + 1 until  hi) {
            var j = i
            while (j > lo && less(array[j], array[j - 1], comparator)) {
                swap(array, j, j - 1)
                j--
            }
        }
    }
}