package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.utils.shuffle
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun `undirected graph`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val expected = """
            13 vertices, 13 edges 
            0: 6 5 2 1 
            1: 0 
            2: 0 
            3: 5 4 
            4: 6 5 3 
            5: 4 3 0 
            6: 4 0 
            7: 8 
            8: 7 
            9: 12 11 10 
            10: 9 
            11: 12 9 
            12: 11 9 
            """.trimIndent()

        assertEquals(expected, graph.toString())
    }

    @Test
    fun `depth-first-search`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val search = DepthFirstSearch(graph, 0)
        val markedVertices = mutableListOf<Int>()
        for (v in 0 until graph.vertices) {
            if (search.marked(v)) {
                markedVertices.add(v)
            }
        }
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6), markedVertices)
        assertTrue(search.count != graph.vertices)
    }

    @Test
    fun `depth-first-paths`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val source = 0
        val dfs = DepthFirstPaths(graph, source)
        val sb = StringBuilder()
        for (v in 0 until graph.vertices) {
            sb.append("$source to $v: ")
            if (dfs.hasPathTo(v)) {
                dfs.pathTo(v)?.forEach { x ->
                    if (x != source) {
                        sb.append("-")
                    }
                    sb.append(x)
                }
            }
            if (v != graph.vertices -1) {
                sb.append('\n')
            }
        }
        val expected =
            """
                0 to 0: 0
                0 to 1: 0-1
                0 to 2: 0-2
                0 to 3: 0-6-4-5-3
                0 to 4: 0-6-4
                0 to 5: 0-6-4-5
                0 to 6: 0-6
                0 to 7: 
                0 to 8: 
                0 to 9: 
                0 to 10: 
                0 to 11: 
                0 to 12: 
            """.trimIndent()
        assertEquals(expected, sb.toString())
    }

    @Test
    fun `breadth-first-search`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val search = BreadthFirstSearch(graph, 0)
        val markedVertices = mutableListOf<Int>()
        for (v in 0 until graph.vertices) {
            if (search.marked(v)) {
                markedVertices.add(v)
            }
        }
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6), markedVertices)
        assertTrue(search.count != graph.vertices)
    }

    @Test
    fun `breadth-first-paths`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val source = 0
        val bfs = BreadthFirstPaths(graph, source)
        val sb = StringBuilder()
        for (v in 0 until graph.vertices) {
            sb.append("$source to $v: ")
            if (bfs.hasPathTo(v)) {
                bfs.pathTo(v)?.forEach { x ->
                    if (x != source) {
                        sb.append("-")
                    }
                    sb.append(x)
                }
            }
            if (v != graph.vertices -1) {
                sb.append('\n')
            }
        }
        val expected =
            """
                0 to 0: 0
                0 to 1: 0-1
                0 to 2: 0-2
                0 to 3: 0-5-3
                0 to 4: 0-6-4
                0 to 5: 0-5
                0 to 6: 0-6
                0 to 7: 
                0 to 8: 
                0 to 9: 
                0 to 10: 
                0 to 11: 
                0 to 12: 
            """.trimIndent()
        assertEquals(expected, sb.toString())
    }

    @Test
    fun `connected components`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val cc = ConnectedComponents(graph)
        assertEquals(3, cc.count)
        val components: Array<Array<Int>> = cc.components(graph)
        assertContentEquals(intArrayOf(0, 1, 2, 3, 4, 5, 6), components[0].toIntArray())
        assertContentEquals(intArrayOf(7, 8), components[1].toIntArray())
        assertContentEquals(intArrayOf(9, 10, 11, 12), components[2].toIntArray())
    }

    @Test
    fun `cycle detection`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val finder = Cycle(graph)
        assertTrue(finder.hasCycle())

        finder.cycle?.run {
            val cycle = Array<Int>(this.size()) { 0 }
            forEachIndexed { index, v ->
                cycle[index] = v
            }
            assertContentEquals(intArrayOf(3, 4, 5, 3), cycle.toIntArray())
        }
    }

    @Test
    fun `two colorability`() {
        val graph = Graph(9)
        graph.apply {
            bipartiteGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val bipartite = Bipartite(graph)
        assertTrue(bipartite.isBipartite)
        assertFalse(bipartite.color(0))
        assertFalse(bipartite.color(1))
        assertFalse(bipartite.color(2))
        assertFalse(bipartite.color(3))
        assertFalse(bipartite.color(4))
        assertTrue(bipartite.color(5))
        assertTrue(bipartite.color(6))
        assertTrue(bipartite.color(7))
        assertTrue(bipartite.color(8))
    }

    @Test
    fun `bridge (cut-edge)`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val bridge = Bridge(graph)
        assertEquals(5, bridge.components)
    }

    @Test
    fun `biconnected (cut vertex)`() {
        val graph = Graph(13)
        graph.apply {
            tingGraph.forEach { (v, w) ->
                addEdge(v, w)
            }
        }
        val expectedResults = listOf(
            true,  // 0
            false, // 1
            false, // 2
            false, // 3
            false, // 4
            false, // 5
            false, // 6
            false, // 7
            false, // 8
            true,  // 9
            false, // 10
            false, // 11
            false, // 12
        )
        val biconnected = Biconnected(graph)
        for (v in 0 until graph.vertices) {
            val actual = biconnected.isArticulation(v)
            val expected = expectedResults[v]
            assertEquals(expected, actual, "$v should be $expected")
        }
    }


    companion object {
        /*
         *   0 -------- 6
         *  | \ \      /
         *  | 1  2    /
         *  |        /
         *  |  3    /
         *  | /  \ /
         *  5 --- 4
         *
         *  7 ---- 8
         *
         *   9 ----- 10
         *  |  \
         *  |   \
         *  11 - 12
         */
        val tingGraph = listOf(
            0 to 1,
            0 to 2,
            0 to 5,
            0 to 6,
            3 to 4,
            3 to 5,
            4 to 5,
            4 to 6,
            7 to 8,
            9 to 10,
            9 to 11,
            9 to 12,
            11 to 12,
        )

        val bipartiteGraph = listOf(
            0 to 5,
            0 to 8,
            1 to 7,
            2 to 5,
            2 to 6,
            3 to 7,
            3 to 8,
            4 to 8
        )
    }
}