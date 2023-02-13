package com.elirex.algorithms.utils

import kotlin.random.Random

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


fun <T> shuffle(array: Array<T>) {
    val rand = Random(System.currentTimeMillis())
    for (i in array.indices) {
        val r = rand.nextInt(array.size - i)
        val temp = array[i]
        array[i] = array[r]
        array[r] = temp
    }
}

