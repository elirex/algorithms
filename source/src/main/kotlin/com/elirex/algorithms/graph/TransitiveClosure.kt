package com.elirex.algorithms.graph

class TransitiveClosure(
    graph: DGraph,
) {

    private val tc: Array<DirectedDepthFirstSearch>

    init {
        tc = Array(graph.vertices) { v -> DirectedDepthFirstSearch(graph, v) }
    }

    fun reachable(v: Int, w: Int): Boolean {
        validateVertex(v)
        validateVertex(w)
        return tc[v].marked(w)
    }

    private fun validateVertex(v: Int) {
        val V = tc.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}