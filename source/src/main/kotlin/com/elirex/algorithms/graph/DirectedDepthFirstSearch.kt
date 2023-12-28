package com.elirex.algorithms.graph

class DirectedDepthFirstSearch(
    graph: DGraph,
) {
    private val marked: Array<Boolean>
    var count: Int = 0
        private set
    init {
        marked = Array(graph.vertices) { false }
    }

    constructor(graph: DGraph, source: Int): this(graph) {
        validateVertex(source)
        dfs(graph, source)
    }

    constructor(graph: DGraph, sources: Iterable<Int>): this(graph) {
        validateVertices(sources)
        sources.forEach { v ->
            if(!marked[v]) {
                dfs(graph, v)
            }
        }
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

    private fun validateVertices(vertices: Iterable<Int>) {
        var count = 0
        vertices.forEach { v ->
            count++
            validateVertex(v)
        }
        if (count == 0) {
            throw IllegalArgumentException("zero vertices")
        }
    }
}