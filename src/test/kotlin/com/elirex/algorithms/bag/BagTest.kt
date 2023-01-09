package com.elirex.algorithms.bag

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BagTest {

    @Test
    fun `linked list bag`() {
        val bag: Bag<String>  = LinkedListBag<String>()
        assertTrue(bag.isEmpty())

        bag.add("A")
        assertEquals(1, bag.size())

        bag.add("B")
        bag.add("C")
        bag.add("D")
        bag.add("E")

        for (item: String in bag) {
            assertTrue(setOf("E", "D", "C", "B", "A").contains(item))
        }
    }

    @Test
    fun `array bag`() {
        val bag: Bag<String>  = ArrayBag()
        assertTrue(bag.isEmpty())

        bag.add("A")
        assertEquals(1, bag.size())

        bag.add("B")
        bag.add("C")
        bag.add("D")
        bag.add("E")

        for (item: String in bag) {
            assertTrue(setOf("E", "D", "C", "B", "A").contains(item))
        }
    }

    @Test
    fun `resize array bag`() {
        val bag: ArrayBag<String>  = ArrayBag()
        assertEquals(8, bag.capacity())
        bag.resize(10)
        assertEquals(10, bag.capacity())
    }
}