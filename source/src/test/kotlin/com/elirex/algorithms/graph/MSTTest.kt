package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MSTTest {


    @Test
    fun `lazy prim MST`() {
        val graph = EdgeWeightedGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }
        val mst = LazyPrimMST(graph)
        assertEquals(1.81000, mst.weight)
        assertEquals(
            expected = listOf(
                Edge(v=0, w=7, weight=0.16),
                Edge(v=1, w=7, weight=0.19),
                Edge(v=0, w=2, weight=0.26),
                Edge(v=2, w=3, weight=0.17),
                Edge(v=5, w=7, weight=0.28),
                Edge(v=4, w=5, weight=0.35),
                Edge(v=6, w=2, weight=0.4),
            ),
            actual = mst.edges().toList()
        )
    }

    @Test
    fun `eager prim MST`() {
        val graph = EdgeWeightedGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }
        val mst = EagerPrimMST(graph)
        assertEquals(1.81000, mst.weight)
        assertEquals(
            expected = listOf(
                Edge(v=1, w=7, weight=0.19),
                Edge(v=0, w=2, weight=0.26),
                Edge(v=2, w=3, weight=0.17),
                Edge(v=4, w=5, weight=0.35),
                Edge(v=5, w=7, weight=0.28),
                Edge(v=6, w=2, weight=0.4),
                Edge(v=0, w=7, weight=0.16),
            ),
            actual = mst.edges().toList()
        )
    }

    @Test
    fun `kruskal prim MST`() {
        val graph = EdgeWeightedGraph(8).apply {
            tinyEWG.forEach { e ->
                addEdge(e)
            }
        }
        val mst = KruskalMST(graph)
        assertEquals(1.81000, mst.weight)
        assertEquals(
            expected = listOf(
                Edge(v=0, w=7, weight=0.16),
                Edge(v=2, w=3, weight=0.17),
                Edge(v=1, w=7, weight=0.19),
                Edge(v=0, w=2, weight=0.26),
                Edge(v=5, w=7, weight=0.28),
                Edge(v=4, w=5, weight=0.35),
                Edge(v=6, w=2, weight=0.4),
            ),
            actual = mst.edges().toList()
        )
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