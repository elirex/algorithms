package com.elirex.algorithms.queue

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QueueTest {

    @Test
    fun `linked list queue`() {
        val queue: Queue<String> = LinkedListQueue()
        assertTrue(queue.isEmpty())
        queue.run {
            enqueue("A")
            enqueue("B")
            enqueue("C")
            enqueue("D")
            enqueue("E")
            enqueue("F")
            enqueue("G")
            enqueue("H")
            enqueue("I")
        }

        listOf("A", "B", "C", "D", "E", "F", "G", "H", "I").forEach { expected: String ->
            assertEquals(expected, queue.dequeue())
        }
    }

    @Test
    fun `array queue`() {
        val queue: Queue<String> = ArrayQueue()
        assertTrue(queue.isEmpty())
        queue.run {
            enqueue("A")
            enqueue("B")
            enqueue("C")
            enqueue("D")
            enqueue("E")
        }

        listOf("A", "B", "C", "D", "E").forEach { expected: String ->
            assertEquals(expected, queue.dequeue())
        }
    }


    @Test
    fun `resize array queue`() {
        val queue = ArrayQueue<String>()
        assertEquals(8, queue.capacity())
        queue.resize(10)
        assertEquals(10, queue.capacity())
    }
}