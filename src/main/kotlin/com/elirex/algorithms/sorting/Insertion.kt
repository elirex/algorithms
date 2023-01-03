package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object Insertion {

    fun <T : Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        for (i in 1 until n) {
            for (j in i downTo 1) {
                if (less(array[j], array[j - 1], comparator)) {
                    swap(array, j, j - 1)
                }
            }
        }
    }
}