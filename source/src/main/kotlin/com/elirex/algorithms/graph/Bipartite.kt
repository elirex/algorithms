package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class Bipartite(
    graph: Graph,
) {
    var isBipartite: Boolean = true
        private set
    var cycle: Stack<Int>? = null
        private set
    private val color: Array<Boolean>
    private val marked: Array<Boolean>
    private val edgeTo: Array<Int>


    init {
        color = Array(graph.vertices) { false }
        marked = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { -1 }
        for (v in 0 until graph.vertices) {
            if (!marked[v]) {
                dfs(graph, v)
            }
        }
    }

    private fun dfs(graph: Graph, v: Int) {
        marked[v] = true
        graph.adjacent(v).forEach { w ->
            // short circuit if odd-length cycle found
            if (cycle != null) {
                return
            }

            // found uncolored vertex, so recur
            if (!marked[w]) {
                edgeTo[w] = v
                color[w] = !color[v]
                dfs(graph, w)
            } else if (color[w] == color[v]) {
                isBipartite = false
                cycle = LinkedListStack<Int>().apply {
                    push(w)
                    var x = v
                    while (x != w) {
                        push(x)
                        x = edgeTo[x]
                    }
                    push(w)
                }
            }
        }
    }

    fun color(v: Int): Boolean {
        validateVertex(v)
        if (!isBipartite) {
            throw UnsupportedOperationException("graph is not bipartite ")
        }
        return color[v]
    }

    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}