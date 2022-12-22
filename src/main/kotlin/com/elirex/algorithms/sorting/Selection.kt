package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object Selection {

    fun <T : Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        for (i in 0 until n) {
            var min = i
            for (j in i + 1 until n) {
                if ((comparator != null && less(array[j], array[min], comparator)) ||
                    less(array[j], array[min])) {
                    min = j
                }
            }
            if (min != i) {
                swap(array, i, min)
            }
        }
    }

}

