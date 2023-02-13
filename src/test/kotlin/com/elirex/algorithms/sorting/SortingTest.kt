package com.elirex.algorithms.sorting

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class SortingTest {

    companion object {
        private val inputString = arrayOf("S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E")
        private val expectedString = arrayOf("A", "E", "E", "E", "H", "L", "L", "L", "M", "O", "P", "R", "S", "S", "T", "X")
        private val inputInt = arrayOf(18, 7, 4, 11, 11, 18, 14 ,17, 19, 4, 23, 0, 12, 15, 11, 4)
        private val expectedInt = arrayOf(0, 4, 4, 4, 7, 11, 11, 11, 12 ,14, 15, 17, 18, 18, 19, 23)
    }

    @Test
    fun `selection sort`() {
        val array = inputString.clone()
        Selection.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `selection sort with comparator`() {
        val array = inputInt
        Selection.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)

    }

    @Test
    fun `insertion sort`() {
        val array = inputString.clone()
        Insertion.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `insertion sort with comparator`() {
        val array = inputInt.clone()
        Insertion.sort(array) { o1, o2 -> o2 - o1}
        assertContentEquals(expectedInt.reversedArray(), array)
    }

    @Test
    fun `shell sort`() {
        val array = inputString.clone()
        Shell.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `shell sort with comparator`() {
        val array = inputInt.clone()
        Shell.sort(array) { o1, o2 -> o2 - o1}
        assertContentEquals(expectedInt.reversedArray(), array)
    }


    @Test
    fun `top-down merge sort`() {
        val array = inputString.clone()
        MergeTopDown.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `top-down merge sort with comparator`() {
        val array = inputInt.clone()
        MergeTopDown.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)

    }

    @Test
    fun `bottom-up merge sort`() {
        val array = inputString.clone()
        MergeBottomUp.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `bottom-up merge sort with comparator`() {
        val array = inputInt.clone()
        MergeBottomUp.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)

    }

    @Test
    fun `merge sort`() {
        val array = inputString.clone()
        Merge.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `merge sort with comparator`() {
        val array = inputInt.clone()
        Merge.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)

    }

    @Test
    fun `quick sort`() {
        val array = inputString.clone()
        Quick.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `quick sort with comparator`() {
        val array = inputInt.clone()
        Quick.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)

    }

    @Test
    fun `quick sort median-of-3`() {
        val array = inputString.clone()
        QuickMedianOf3.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `quick sort median-of-3 with comparator`() {
        val array = inputInt.clone()
        QuickMedianOf3.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)
    }

    @Test
    fun `quick sort 3-way`() {
        val array = inputString.clone()
        Quick3way.sort(array)
        assertContentEquals(expectedString, array)
    }

    @Test
    fun `quick sort 3-way with comparator`() {
        val array = inputInt.clone()
        Quick3way.sort(array) { o1, o2 -> o2 - o1 }
        assertContentEquals(expectedInt.reversedArray(), array)
    }
}