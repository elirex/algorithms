package com.elirex.algorithms.sorting

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class SortingTest {

    companion object {
        private val input = arrayOf("S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E")
        private val expected = arrayOf("A", "E", "E", "E", "H", "L", "L", "L", "M", "O", "P", "R", "S", "S", "T", "X")
    }

    @Test
    fun `selection sort`() {
        val array = input.clone()
        Selection.sort(array)
        assertContentEquals(expected, array)

    }

    @Test
    fun `insertion sort`() {
        val array = input.clone()
        Insertion.sort(array)
        assertContentEquals(expected, array)

    }

    @Test
    fun `shell sort`() {
        val array = input.clone()
        Shell.sort(array)
        assertContentEquals(expected, array)

    }
}