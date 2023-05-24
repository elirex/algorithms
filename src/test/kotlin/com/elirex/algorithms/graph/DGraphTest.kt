package com.elirex.algorithms.graph

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class DGraphTest {

    @Test
    fun `directed graph`() {
        val graph = DGraph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val expected = listOf(
            listOf(5, 1), // 0
            emptyList(), // 1
            listOf(0, 3), // 2
            listOf(5, 2), // 3
            listOf(3, 2), // 4
            listOf(4), // 5
            listOf(9, 4, 8, 0), // 6
            listOf(6, 9), // 7
            listOf(6), // 8
            listOf(11, 10), // 9
            listOf(12), // 10
            listOf(4, 12), // 11
            listOf(9), // 12
        )
        for (v in 0 until graph.vertices) {
            assertContentEquals(expected[v], graph.adjacent(v), "$v vertex")
        }
    }

    @TestFactory
    fun `directed depth-first-search`(): List<DynamicTest> {
        val graph = DGraph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        data class Case(
            val sourceVertex: Int,
            val expected: List<Int>, // expected reachable vertices
        )
        val testCases = listOf(
            Case(
                sourceVertex = 1,
                expected = listOf(1)
            ),
            Case(
                sourceVertex = 2,
                expected = listOf(0, 1, 2, 3, 4, 5)
            ),
        )
        return testCases.mapIndexed { i, (source, expected) ->
            DynamicTest.dynamicTest("case $i should be $expected") {
                val dfs = DirectedDepthFirstSearch(graph, source)
                val actual = mutableListOf<Int>()
                for (v in 0 until graph.vertices) {
                    if (dfs.marked(v)) {
                        actual.add(v)
                    }
                }
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `depth-first directed paths`() {
        val graph = DGraph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }

        val expecteds= listOf(
            listOf(3, 5, 4, 2, 0), // 3 to 0
            listOf(3, 5, 4, 2, 0, 1), // 3 to 1
            listOf(3, 5, 4, 2), // 3 to 2
            listOf(3), // 3 to 3
            listOf(3, 5, 4), // 3 to 4
            listOf(3, 5), // 3 to 5
            emptyList(), // 3 to 6
            emptyList(), // 3 to 7
            emptyList(), // 3 to 8
            emptyList(), // 3 to 9
            emptyList(), // 3 to 10
            emptyList(), // 3 to 11
            emptyList(), // 3 to 12
        )
        val dfs = DepthFirstDirectedPaths(graph, 3)
        for (v in 0 until graph.vertices) {
            val actual = dfs.pathTo(v)?.toList() ?: emptyList()
            assertContentEquals(expecteds[v], actual, "3 to $v")
        }
    }

    companion object {
        /*
         * 0: 5 1
         * 1:
         * 2: 0 3
         * 3: 5 2
         * 4: 3 2
         * 5: 4
         * 6: 9 4 8 0
         * 7: 6 9
         * 8: 6
         * 9: 11 10
         * 10: 12
         * 11: 4 12
         * 12: 9
         */
        val tingGraph = listOf(
            0 to 1,
            0 to 5,
            2 to 3,
            2 to 0,
            3 to 2,
            3 to 5,
            4 to 2,
            4 to 3,
            6 to 0,
            6 to 8,
            6 to 4,
            6 to 9,
            5 to 4,
            7 to 9,
            7 to 6,
            8 to 6,
            9 to 10,
            9 to 11,
            10 to 12,
            11 to 12,
            11 to 4,
            12 to 9
        )
    }
}