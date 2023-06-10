package com.elirex.algorithms.sorting

import com.elirex.algorithms.extensions.swap
import com.elirex.algorithms.utils.less

object Heap {

    fun <T: Comparable<T>> sort(array: Array<T>, comparator: Comparator<T>? = null) {
        val n = array.size - 1
        for (k in n/2 downTo 0) {
            sink(array, k, n, comparator)
        }
        var k = n
        while (k > 0) {
            array.swap(0, k--)
            sink(array, 0, k, comparator)
        }
    }

    private fun <T: Comparable<T>> sink(
        pq: Array<T>,
        k: Int,
        n: Int,
        comparator: Comparator<T>?
    ) {
        var i = k
        while (2 * i <= n) {
            var j = 2 * i
            if (j < n && less(pq[j], pq[j + 1], comparator)) {
                j++
            }
            if (!less(pq[i], pq[j], comparator)) {
                break
            }
            pq.swap(i, j)
            i = j
        }
    }
}