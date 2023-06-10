package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

object Shell {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size

        var h = 1
        while (h < n / 3) {
            h = h * 3 + 1
        }

        while (h >= 1) {
            for (i in h until n) {
                for (j in i downTo h step h) {
                    if (less(array[j], array[j - h], comparator)) {
                        swap(array, j, j - h)
                    }
                }
            }
            h /= 3
        }
    }
}