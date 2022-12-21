package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object Insertion {

    fun <T : Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        for (i in 1 until n) {
            var j = i
            while (j > 0 && array[j] < array[j - 1]) {
                if (comparator != null) {
                    less(array[j], array[j - 1], comparator)
                } else {
                    less(array[j], array[j - 1])
                }.let {
                    if (it) {
                        swap(array, j, --j)
                    }
                }
            }
        }
    }
}