package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.assertEquals

class SPTest {

    @Test
    fun `dijkstra short paths`() {
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }
        val source = 0
        val sp: DijkstraShortestPaths = DijkstraShortestPaths(graph, source)

        val expectedHasPathToAndDistance = listOf(
            true to 0.0, // 0 to 0
            true to 1.05, // 0 to 1
            true to 0.26, // 0 to 2
            true to 0.99, // 0 to 3
            true to 0.38, // 0 to 4
            true to 0.73, // 0 to 5
            true to 1.51, // 0 to 6
            true to 0.6, // 0 to 7
        )

        val expectedPath = listOf(
            // 0 to 0
            emptyList(),
            // 0 to 1
            listOf(
                DirectedEdge(0,  4,  0.38),
                DirectedEdge(4,  5,  0.35),
                DirectedEdge(5,  1,  0.32),
            ),
            // 0 to 2
            listOf(
                DirectedEdge(0,  2,  0.26),
            ),
            // 0 to 3
            listOf(
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
                DirectedEdge(7,  3,  0.39),
            ),
            // 0 to 4
            listOf(
                DirectedEdge(0,  4,  0.38),
            ),
            // 0 to 5
            listOf(
                DirectedEdge(0,  4,  0.38),
                DirectedEdge(4,  5,  0.35),
            ),
            // 0 to 6
            listOf(
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
                DirectedEdge(7,  3,  0.39),
                DirectedEdge(3,  6,  0.52),
            ),
            // 0 to 7
            listOf(
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
            ),
        )

        val df = DecimalFormat("#.##").apply {
            roundingMode = RoundingMode.HALF_UP
        }
        for (v in 0 until graph.vertices) {
            val (expectedHasPathTo, expectedDistance) = expectedHasPathToAndDistance[v]
            assertEquals(expectedHasPathTo, sp.hasPathTo(v))
            assertEquals(df.format(expectedDistance), df.format(sp.distanceTo(v)))

            assertEquals(expectedPath[v], sp.pathTo(v)?.toList())
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