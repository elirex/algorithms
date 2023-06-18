package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EdgeWeightedGraphTest {

    @Test
    fun `edge weight graph`() {
        val graph = EdgeWeightedGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }

        val expected = listOf(
            listOf(
                Edge(6, 0, 0.58),
                Edge(0, 2, 0.26),
                Edge(0, 4, 0.38),
                Edge(0, 7, 0.16),
            ),
            listOf(
                Edge(1, 3, 0.29),
                Edge(1, 2, 0.36),
                Edge(1, 7, 0.19),
                Edge(1, 5, 0.32),
            ),
            listOf(
                Edge(6, 2, 0.40),
                Edge(2, 7, 0.34),
                Edge(1, 2, 0.36),
                Edge(0, 2, 0.26),
                Edge(2, 3, 0.17),
            ),
            listOf(
                Edge(3, 6, 0.52),
                Edge(1, 3, 0.29),
                Edge(2, 3, 0.17),
            ),
            listOf(
                Edge(6, 4, 0.93),
                Edge(0, 4, 0.38),
                Edge(4, 7, 0.37),
                Edge(4, 5, 0.35),
            ),
            listOf(
                Edge(1, 5, 0.32),
                Edge(5, 7, 0.28),
                Edge(4, 5, 0.35),
            ),
            listOf(
                Edge(6, 4, 0.93),
                Edge(6, 0, 0.58),
                Edge(3, 6, 0.52),
                Edge(6, 2, 0.40),
            ),
            listOf(
                Edge(2, 7, 0.34),
                Edge(1, 7, 0.19),
                Edge(0, 7, 0.16),
                Edge(5, 7, 0.28),
                Edge(4, 7, 0.37),
            ),
        )

        for (v in 0 until graph.vertices) {
            assertEquals(expected[v], graph.adjacent(v).toList())
        }
    }

    companion object {
        // total vertices 8
        // total edges 16
        private val tinyEWG = listOf<Edge>(
            Edge(4, 5, 0.35),
            Edge(4, 7, 0.37),
            Edge(5, 7, 0.28),
            Edge(0, 7, 0.16),
            Edge(1, 5, 0.32),
            Edge(0, 4, 0.38),
            Edge(2, 3, 0.17),
            Edge(1, 7, 0.19),
            Edge(0, 2, 0.26),
            Edge(1, 2, 0.36),
            Edge(1, 3, 0.29),
            Edge(2, 7, 0.34),
            Edge(6, 2, 0.40),
            Edge(3, 6, 0.52),
            Edge(6, 0, 0.58),
            Edge(6, 4, 0.93),
        )
    }
}