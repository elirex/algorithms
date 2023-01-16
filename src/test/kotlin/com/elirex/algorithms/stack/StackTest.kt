package com.elirex.algorithms.stack

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StackTest {

    @Test
    fun `linked list stack`() {
        val stack: Stack<String> = LinkedListStack<String>()
        assertTrue(stack.isEmpty())
        stack.run {
            push("A")
            push("B")
            push("C")
            push("D")
            push("E")
        }

        listOf("E", "D", "C", "B", "A").forEach { expect: String ->
            assertEquals(expect, stack.pop())
        }
    }

    @Test
    fun `array stack`() {
        val stack: Stack<String> = ArrayStack()
        assertTrue(stack.isEmpty())
        stack.run {
            push("A")
            push("B")
            push("C")
            push("D")
            push("E")
        }

        listOf("E", "D", "C", "B", "A").forEach { expect: String ->
            assertEquals(expect, stack.pop())
        }
    }

    @Test
    fun `resize array bag`() {
        val stack = ArrayStack<String>()
        assertEquals(8, stack.capacity())
        stack.resize(10)
        assertEquals(10, stack.capacity())
    }
}