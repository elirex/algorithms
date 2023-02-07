package com.elirex.algorithms.sorting

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap
import kotlin.random.Random

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


    private fun <T> shuffle(array: Array<T>) {
        val rand = Random(System.currentTimeMillis())
        for (i in array.indices) {
            val r = rand.nextInt(array.size - i)
            val temp = array[i]
            array[i] = array[r]
            array[r] = temp
        }
    }

}