package com.elirex.algorithms.graph

class DirectedDepthFirstSearch(
    graph: DGraph,
    source: Int,
) {

    private val marked: Array<Boolean>
    var count: Int = 0
        private set

    init {
        marked = Array(graph.vertices) { false }
        validateVertex(source)
        dfs(graph, source)
    }

    private fun dfs(graph: DGraph, v: Int) {
        marked[v] = true
        count++
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
    }

    fun marked(v: Int): Boolean {
        validateVertex(v)
        return marked[v]
    }

    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}