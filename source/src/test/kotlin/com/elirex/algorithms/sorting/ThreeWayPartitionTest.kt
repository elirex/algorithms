package com.elirex.algorithms.sorting

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


class ThreeWayPartitionTest {

    @Test
    fun `three way partition int array`() {
        val array = intArrayOf(4, 9, 4, 2, 1, 11, 3, 4, 10, 4, 4, 1, 4)
        val partitionPair = threeWayPartition(array, 4)
        assertEquals(4, partitionPair.first)
        assertEquals(9, partitionPair.second)
        assertContentEquals(intArrayOf(2, 1, 1, 3, 4, 4, 4, 4, 4, 4, 10, 11, 9), array)
    }

    @Test
    fun `three way partition typed array`() {
        val array: Array<Int> = arrayOf(4, 9, 4, 2, 1, 11, 3, 4, 10, 4, 4, 1, 4)
        val partitionPair = threeWayPartition(array, 4)
        assertEquals(4, partitionPair.first)
        assertEquals(9, partitionPair.second)
        assertContentEquals(arrayOf(2, 1, 1, 3, 4, 4, 4, 4, 4, 4, 10, 11, 9), array)
    }
}