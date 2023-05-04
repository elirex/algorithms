package com.elirex.algorithms.graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun `test undirected graph`() {
        val graph = UndirectedGraph(13)
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

    companion object {
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