package com.elirex.algorithms.bag

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BagTest {

    @Test
    fun `linked bag`() {
        val bag: Bag<String>  = LinkedBag<String>()
        assertTrue(bag.isEmpty())

        bag.add("A")
        assertEquals(1, bag.size())

        bag.add("B")
        bag.add("C")
        bag.add("D")
        bag.add("E")

        val actual = mutableListOf<String>()
        for (item: String in bag) {
            actual.add(item)
        }
        assertEquals(listOf("E", "D", "C", "B", "A"), actual)
    }
}