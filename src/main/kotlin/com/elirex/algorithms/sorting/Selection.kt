package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.swap

object Selection {

    fun <T : Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size
        for (i in 0 until n) {
            var min = i
            for (j in i + 1 until n) {
                if (comparator != null) {
                    if (comparator.compare(array[j], array[i]) < 0 ) {
                        min = j
                    }
                } else {
                    if (array[j] < array[min]) {
                        min = j
                    }
                }
            }
            swap(array, i, min)
        }
    }

}

