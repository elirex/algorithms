package com.elirex.algorithms.graph

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun `test undirected graph`() {
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
    fun `test depth-first-search`() {
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
    fun `test depth-first-paths`() {
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
    }
}