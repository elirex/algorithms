package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

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
            listOf(1, 5), // 0
            listOf(), // 1
            listOf(3, 0), // 2
            listOf(2, 5), // 3
            listOf(2, 3), // 4
            listOf(4), // 5
            listOf(0, 8, 4, 9), // 6
            listOf(9, 6), // 7
            listOf(6), // 8
            listOf(10, 11), // 9
            listOf(12), // 10
            listOf(12, 4), // 11
            listOf(9), // 12
        )
        for (v in 0 until graph.vertices) {
            assertContentEquals(expected[v], graph.adjacent(v))
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
            0 to 5,
            0 to 1,
            2 to 0,
            2 to 3,
            3 to 5,
            3 to 2,
            4 to 3,
            4 to 2,
            5 to 4,
            6 to 9,
            6 to 4,
            6 to 8,
            6 to 0,
            7 to 6,
            7 to 9,
            8 to 6,
            9 to 11,
            9 to 10,
            10 to 12,
            11 to 4,
            11 to 12,
            12 to 9
        )
    }
}