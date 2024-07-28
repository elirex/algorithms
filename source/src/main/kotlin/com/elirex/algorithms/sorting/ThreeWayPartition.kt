package com.elirex.algorithms.sorting

import com.elirex.algorithms.extensions.swap

fun threeWayPartition(array: IntArray, pivot: Int): Pair<Int, Int> {
    val n = array.size
    return partition(array, pivot, 0, n - 1)
}

fun threeWayPartition(array: IntArray, lowPivot: Int, highPivot: Int): Pair<Int, Int> {
    val n = array.size
    var p1 = partition(array, lowPivot, 0, n - 1)
    var p2 = partition(array, highPivot, p1.second + 1, n - 1)
    return Pair(p1.first, p2.second)
}

private fun partition(
    array: IntArray,
    pivot: Int,
    start: Int,
    end: Int,
): Pair<Int, Int> {
    var i = start
    var left = start
    var right = end
    while (i <= right) {
        if (array[i] < pivot) {
            array.swap(i++, left++)
        } else if (array[i] > pivot) {
            array.swap(i, right--)
        } else {
            i++
        }
    }
    return Pair(left, right)
}

fun <T : Comparable<T>> threeWayPartition(array: Array<T>, pivot: T): Pair<Int, Int> {
    val n = array.size
    return typedArrayPartition(array, pivot, 0, n - 1)
}

private fun <T: Comparable<T>> typedArrayPartition(
    array: Array<T>,
    pivot: T,
    start: Int,
    end: Int,
): Pair<Int, Int> {
    var left = start
    var right = end
    var i: Int = 0
    while (i <= right) {
        if (array[i] < pivot) {
            array.swap(i++, left++)
        } else if (array[i] > pivot) {
            array.swap(i, right--)
        } else {
            i++
        }
    }
    return Pair(left, right)
}


fun main() {
    val array = intArrayOf(4, 9, 4, 2, 1, 11, 3, 4, 10, 4, 4, 1, 4)
    val typedArray = array.toTypedArray()
    threeWayPartition(array, 4)
    threeWayPartition(typedArray, 4)

    println(array.contentToString())
    println(typedArray.contentToString())
}