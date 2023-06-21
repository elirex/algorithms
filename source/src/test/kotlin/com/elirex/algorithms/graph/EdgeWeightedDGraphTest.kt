package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EdgeWeightedDGraphTest {

    @Test
    fun `edge weight graph`() {
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }

        val expected = listOf(
            listOf(
                DirectedEdge(0, 2, 0.26),
                DirectedEdge(0, 4, 0.38),
            ),
            listOf(
                DirectedEdge(1, 3, 0.29),
            ),
            listOf(
                DirectedEdge(2, 7, 0.34),
            ),
            listOf(
                DirectedEdge(3, 6, 0.52),
            ),
            listOf(
                DirectedEdge(4, 7, 0.37),
                DirectedEdge(4, 5, 0.35),
            ),
            listOf(
                DirectedEdge(5, 1, 0.32),
                DirectedEdge(5, 7, 0.28),
                DirectedEdge(5, 4, 0.35),
            ),
            listOf(
                DirectedEdge(6, 4, 0.93),
                DirectedEdge(6, 0, 0.58),
                DirectedEdge(6, 2, 0.40),
            ),
            listOf(
                DirectedEdge(7, 3, 0.39),
                DirectedEdge(7, 5, 0.28),
            ),
        )

        for (v in 0 until graph.vertices) {
            assertEquals(expected[v], graph.adjacent(v).toList())
        }
    }

    companion object {
        // total vertices 8
        // total edges 15
        private val tinyEWG = listOf<DirectedEdge>(
            DirectedEdge(4,  5,  0.35),
            DirectedEdge(5,  4,  0.35),
            DirectedEdge(4,  7,  0.37),
            DirectedEdge(5,  7,  0.28),
            DirectedEdge(7,  5,  0.28),
            DirectedEdge(5,  1,  0.32),
            DirectedEdge(0,  4,  0.38),
            DirectedEdge(0,  2,  0.26),
            DirectedEdge(7,  3,  0.39),
            DirectedEdge(1,  3,  0.29),
            DirectedEdge(2,  7,  0.34),
            DirectedEdge(6,  2,  0.40),
            DirectedEdge(3,  6,  0.52),
            DirectedEdge(6,  0,  0.58),
            DirectedEdge(6,  4,  0.93),
        )
    }
}