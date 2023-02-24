package com.elirex.algorithms.extensions

import kotlin.random.Random

fun <T> Array<T>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp

}

fun <T> Array<T>.shuffle() {
    val rand = Random(System.currentTimeMillis())
    for (i in indices) {
        swap(i, rand.nextInt(size - i))
    }
}
