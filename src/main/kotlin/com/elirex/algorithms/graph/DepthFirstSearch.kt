package com.elirex.algorithms.graph

class DepthFirstSearch(
    graph: Graph,
    source: Int,
) {
    private val marked: Array<Boolean>
    var count: Int
        private set

    init {
        marked = Array(graph.vertices) { false }
        count = 0
        dfs(graph, source)
    }

    fun marked(v: Int): Boolean {
        validateVertex(v)
        return marked[v]
    }

    private fun dfs(graph: Graph, v: Int) {
        count++
        marked[v] = true
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
    }

    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v >= V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }

}