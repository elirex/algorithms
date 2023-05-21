package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class Cycle(
    graph: Graph,
) {

    private lateinit var marked: Array<Boolean>
    private lateinit var edgeTo: Array<Int>
    var cycle: Stack<Int>? = null
        private set

    init {
        if (!hasParallelEdges(graph)) {
            marked = Array(graph.vertices) { false }
            edgeTo = Array(graph.vertices) { 0 }

            for (v in 0 until  graph.vertices) {
                if (!marked[v]) {
                    dfs(graph, - 1, v)
                }
            }
        }
    }

    private fun dfs(graph: Graph, u: Int, v: Int) {
        marked[v] = true
        graph.adjacent(v).forEach { w ->
            // short circuit if cycle already found
            if (cycle != null) {
                return
            }

            if (!marked[w]) {
                edgeTo[w] = v
                dfs(graph, v, w)
            } else if (w != u) {
                cycle = LinkedListStack<Int>().apply {
                    var x = v
                    while (x != w){
                        push(x)
                        x = edgeTo[x]
                    }
                    push(w)
                    push(v)
                }
            }
        }
    }

    private fun hasParallelEdges(graph: Graph): Boolean {
        marked = Array(graph.vertices) { false }
        for (v in 0 until  graph.vertices) {
            graph.adjacent(v).forEach { w ->
                if (marked[w]) {
                    cycle = LinkedListStack<Int>().apply {
                        push(v)
                        push(w)
                        push(v)
                    }
                    return true
                }
                marked[w] = true
            }

            graph.adjacent(v).forEach { w ->
                marked[w] = false
            }
        }
        return false
    }

    fun hasCycle(): Boolean {
        return cycle != null
    }
}