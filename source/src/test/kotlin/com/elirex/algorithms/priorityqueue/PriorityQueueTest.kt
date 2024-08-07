package com.elirex.algorithms.priorityqueue

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PriorityQueueTest {

    @Test
    fun `ordered array priority queue`() {
        val pq: PQ<String> = OrderedArrayPQ(8)
        generateTestCases().forEach { case ->
            when (case) {
                is TestCase.Push -> {
                    pq.push(case.data)
                    assertEquals(case.expected, pq.size())
                }
                is TestCase.Pop -> {
                    assertEquals(case.expected, pq.pop())
                }
                is TestCase.Peek -> {
                    assertEquals(case.expected, pq.peek())
                }
                is TestCase.Size -> {
                    assertEquals(case.expected, pq.size())
                }
            }
        }
    }

    @Test
    fun `unordered array priority queue`() {
        val pq: PQ<String> = UnorderedArrayPQ(8)
        generateTestCases().forEach { case ->
            when (case) {
                is TestCase.Push -> {
                    pq.push(case.data)
                    assertEquals(case.expected, pq.size())
                }
                is TestCase.Pop -> {
                    assertEquals(case.expected, pq.pop())
                }
                is TestCase.Peek -> {
                    assertEquals(case.expected, pq.peek())
                }
                is TestCase.Size -> {
                    assertEquals(case.expected, pq.size())
                }
            }
        }
    }

    @Test
    fun `heap priority queue`() {
        val pq: PQ<String> = HeapPQ<String>()
        generateTestCases().forEach { case ->
            when (case) {
                is TestCase.Push -> {
                    pq.push(case.data)
                    assertEquals(case.expected, pq.size())
                }
                is TestCase.Pop -> {
                    assertEquals(case.expected, pq.pop())
                }
                is TestCase.Peek -> {
                    assertEquals(case.expected, pq.peek())
                }
                is TestCase.Size -> {
                    assertEquals(case.expected, pq.size())
                }
            }
        }
    }

    private fun generateTestCases() = listOf(
        TestCase.Push("P", 1),
        TestCase.Push("Q", 2),
        TestCase.Push("E", 3),
        TestCase.Pop("Q"),
        TestCase.Size(2),
        TestCase.Push("X", 3),
        TestCase.Peek("X"),
        TestCase.Push("A", 4),
        TestCase.Push("M", 5),
        TestCase.Pop("X"),
        TestCase.Size(4),
        TestCase.Push("P", 5),
        TestCase.Push("L", 6),
        TestCase.Push("E", 7),
        TestCase.Pop("P"),
        TestCase.Size(6),
    )

    private sealed interface TestCase<T> {
        data class Push<T>(
            val data: T,
            val expected: Int
        ) : TestCase<T>
        data class Peek<T>(
            val expected: T
        ) : TestCase<T>
        data class Pop<T>(
            val expected: T,
        ) : TestCase<T>
        data class Size<T>(
            val expected: Int,
        ) : TestCase<T>
    }
}