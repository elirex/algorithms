package com.elirex.algorithms.utils

fun <T> swap(array: Array<T>, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}

fun <T: Comparable<T>> less(a: T, b: T, comparator: Comparator<T>? = null): Boolean {
    return when (comparator) {
        null -> a < b
        else -> comparator.compare(a, b) < 0
    }
}

