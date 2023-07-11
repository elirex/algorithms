package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.assertEquals

class SPTest {

    @Test
    fun `dijkstra short paths`() {
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWDG.forEach { e ->
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

    @Test
    fun `acyclic shortest paths`() {
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWDAG.forEach { e ->
                addEdge(e)
            }
        }
        val source = 5
        val sp: AcyclicShortestPaths = AcyclicShortestPaths(graph, source)

        val expectedHasPathToAndDistance = listOf(
            true to 0.73, // 5 to 0
            true to 0.32, // 5 to 1
            true to 0.62, // 5 to 2
            true to 0.61, // 5 to 3
            true to 0.35, // 5 to 4
            true to 0.0, // 5 to 5
            true to 1.13, // 5 to 6
            true to 0.28, // 5 to 7
        )

        val expectedPath = listOf(
            // 5 to 0
            listOf(
                DirectedEdge(5,  4,  0.35),
                DirectedEdge(4,  0,  0.38),
            ),
            // 5 to 1
            listOf(
                DirectedEdge(5,  1,  0.32),
            ),
            // 5 to 2
            listOf(
                DirectedEdge(5,  7,  0.28),
                DirectedEdge(7,  2,  0.34),
            ),
            // 5 to 3
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
            ),
            // 5 to 4
            listOf(
                DirectedEdge(5,  4,  0.35),
            ),
            // 5 to 5
            emptyList(),
            // 5 to 6
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
            ),
            // 5 to 7
            listOf(
                DirectedEdge(5,  7,  0.28),
            ),
        )

        val df = DecimalFormat("#.##").apply {
            roundingMode = RoundingMode.HALF_UP
        }
        for (v in 0 until graph.vertices) {
            val (expectedHasPathTo, expectedDistance) = expectedHasPathToAndDistance[v]
            assertEquals(expectedHasPathTo, sp.hasPathTo(v), "vertex $v")
            assertEquals(df.format(expectedDistance), df.format(sp.distanceTo(v)), "vertex $v")

            assertEquals(expectedPath[v], sp.pathTo(v)?.toList(), "vertex $v")
        }
    }

    @Test
    fun `acyclic longest paths`() {
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWDAG.forEach { e ->
                addEdge(e)
            }
        }
        val source = 5
        val sp = AcyclicLongestPaths(graph, source)

        val expectedHasPathToAndDistance = listOf(
            true to 2.44, // 5 to 0
            true to 0.32, // 5 to 1
            true to 2.77, // 5 to 2
            true to 0.61, // 5 to 3
            true to 2.06, // 5 to 4
            true to 0.0, // 5 to 5
            true to 1.13, // 5 to 6
            true to 2.43, // 5 to 7
        )

        val expectedPath = listOf(
            // 5 to 0
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  0.93),
                DirectedEdge(4,  0,  0.38),
            ),
            // 5 to 1
            listOf(
                DirectedEdge(5,  1,  0.32),
            ),
            // 5 to 2
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  0.93),
                DirectedEdge(4,  7,  0.37),
                DirectedEdge(7,  2,  0.34),
            ),
            // 5 to 3
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
            ),
            // 5 to 4
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  0.93),
            ),
            // 5 to 5
            emptyList(),
            // 5 to 6
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
            ),
            // 5 to 7
            listOf(
                DirectedEdge(5,  1,  0.32),
                DirectedEdge(1,  3,  0.29),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  0.93),
                DirectedEdge(4,  7,  0.37),
            ),
        )

        val df = DecimalFormat("#.##").apply {
            roundingMode = RoundingMode.HALF_UP
        }
        for (v in 0 until graph.vertices) {
            val (expectedHasPathTo, expectedDistance) = expectedHasPathToAndDistance[v]
            assertEquals(expectedHasPathTo, sp.hasPathTo(v), "vertex $v")
            assertEquals(df.format(expectedDistance), df.format(sp.distanceTo(v)), "vertex $v")

            assertEquals(expectedPath[v], sp.pathTo(v)?.toList(), "vertex $v")
        }
    }

    @Test
    fun `critical path method`() {
        val n = 10
        val source = 2 * n
        val sink = 2 * n + 1

        val graph = EdgeWeightedDGraph(2 * n + 2)
        val durations = listOf(
            41.0,
            51.0,
            50.0,
            36.0,
            38.0,
            45.0,
            21.0,
            32.0,
            32.0,
            29.0,
        )
        val precedenceConstraints = listOf(
            listOf(1, 7, 9),
            listOf(2),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            listOf(3, 8),
            listOf(3, 8),
            listOf(2),
            listOf(4, 6),
        )
        for (i in 0 until n) {
            val duration = durations[i]
            graph.addEdge(DirectedEdge(source, i , 0.0))
            graph.addEdge(DirectedEdge(i + n, sink , 0.0))
            graph.addEdge(DirectedEdge(i, i + n , duration))

            precedenceConstraints[i].forEach { precedent ->
                graph.addEdge(DirectedEdge(n + i, precedent, 0.0))
            }
        }

        val cmp = CriticalPathMethod(graph, source)

        val expected = listOf(
          Pair(0.0, 41.0), // 0
          Pair(41.0, 92.0), //1
          Pair(123.0, 173.0), // 2
          Pair(91.0, 127.0), // 3
          Pair(70.0, 108.0), // 4
          Pair(0.0, 45.0), // 5
          Pair(70.0, 91.0), //6
          Pair(41.0, 73.0), // 7
          Pair(91.0, 123.0), // 8
          Pair(41.0, 70.0), // 9
        )
        val expectedFinishTime = 173.0

        for (i in 0 until n) {
            assertEquals(expected[i], Pair(cmp.distanceTo(i), cmp.distanceTo(i + n)))
        }
        assertEquals(expectedFinishTime, cmp.distanceTo(sink))
    }

    @Test
    fun `bellman ford shortest paths`() {
        val expectedDistance = listOf(
            0.0, // 0 to 0
            0.93, // 0 to 1
            0.26, // 0 to 2
            0.99, // 0 to 3
            0.26, // 0 to 4
            0.61, // 0 to 5
            1.51, // 0 to 6
            0.6, // 0 to 7
        )

        val expectedPath = listOf(
            // 0 to 0
            emptyList(),
            // 0 to 1
            listOf(
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
                DirectedEdge(7,  3,  0.39),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  -1.25),
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
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
                DirectedEdge(7,  3,  0.39),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  -1.25),
            ),
            // 0 to 5
            listOf(
                DirectedEdge(0,  2,  0.26),
                DirectedEdge(2,  7,  0.34),
                DirectedEdge(7,  3,  0.39),
                DirectedEdge(3,  6,  0.52),
                DirectedEdge(6,  4,  -1.25),
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
        val graph = EdgeWeightedDGraph(8).apply {
            tinyEWDGn.forEach { e ->
                addEdge(e)
            }
        }
        val source = 0

        val sp = BellmanFordSP(graph, source)

        for (v in 0 until graph.vertices) {
            assertEquals(df.format(expectedDistance[v]), df.format(sp.distanceTo(v)), "vertex $v")
            assertEquals(expectedPath[v], sp.pathTo(v)?.toList(), "vertex $v")
        }
    }


    companion object {
        private val df = DecimalFormat("#.##").apply {
            roundingMode = RoundingMode.HALF_UP
        }


        // total vertices 8
        // total edges 15
        private val tinyEWDG = listOf<DirectedEdge>(
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

        // total vertices 8
        // total edges 13
        private val tinyEWDAG = listOf<DirectedEdge>(
            DirectedEdge(5,  4,  0.35),
            DirectedEdge(4,  7,  0.37),
            DirectedEdge(5,  7,  0.28),
            DirectedEdge(5,  1,  0.32),
            DirectedEdge(4,  0,  0.38),
            DirectedEdge(0,  2,  0.26),
            DirectedEdge(3,  7,  0.39),
            DirectedEdge(1,  3,  0.29),
            DirectedEdge(7,  2,  0.34),
            DirectedEdge(6,  2,  0.40),
            DirectedEdge(3,  6,  0.52),
            DirectedEdge(6,  0,  0.58),
            DirectedEdge(6,  4,  0.93),
        )

        // total vertices 8
        // total edges 15
        private val tinyEWDGn = listOf<DirectedEdge>(
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
            DirectedEdge(6,  2,  -1.2),
            DirectedEdge(3,  6,  0.52),
            DirectedEdge(6,  0,  -1.4),
            DirectedEdge(6,  4,  -1.25),
        )
    }
}