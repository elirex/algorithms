package com.elirex.algorithms.graph

class KosarajuSharirSCC(
    graph: DGraph
) {

    private val marked: Array<Boolean>
    private val id: Array<Int>
    var count: Int
        private set

    init {
        val reversedGraph = DGraph(graph.vertices)
        for (v in 0 until graph.vertices) {
            graph.adjacent(v).forEach { w ->
                reversedGraph.addEdge(w, v)
            }
        }
        marked = Array(graph.vertices) { false }
        id = Array(graph.vertices) { -1 }
        count = 0
        val dfs = DepthFirstOrder(reversedGraph)
        dfs.reversePost.forEach { v ->
            if (!marked[v]) {
                dfs(graph, v)
                count++
            }
        }
    }

    private fun dfs(graph: DGraph, v: Int) {
        marked[v] = true
        id[v] = count
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
    }

    fun stronglyConnected(v: Int, w: Int): Boolean {
        validateVertex(v)
        validateVertex(w)
        return id[v] == id[w]
    }

    fun id(v: Int): Int {
        validateVertex(v)
        return id[v]
    }


    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}