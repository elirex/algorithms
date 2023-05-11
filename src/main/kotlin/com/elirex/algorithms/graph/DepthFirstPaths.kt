package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack

class DepthFirstPaths(
    graph: Graph,
    private val source: Int
) {
    private val marked: Array<Boolean>
    private val edgeTo: Array<Int>

    init {
        marked = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { -1 }
        validateVertex(source)
        dfs(graph, source)
    }

    private fun dfs(graph: Graph, v: Int) {
        marked[v] = true
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                edgeTo[w] = v
                dfs(graph, w)
            }
        }
    }

    fun hasPathTo(v: Int): Boolean {
        validateVertex(v)
        return marked[v]
    }

    fun pathTo(v: Int): Iterable<Int>? {
        validateVertex(v)
        if (!hasPathTo(v)) {
            return null
        }
        val path = LinkedListStack<Int>()
        var x = v
        while (x != source) {
            path.push(x)
            x = edgeTo[x]
        }
        path.push(source)
        return path
    }

    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}