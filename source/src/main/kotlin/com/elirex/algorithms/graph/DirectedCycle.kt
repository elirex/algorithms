package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class DirectedCycle(
    graph: DGraph
) {

    private val marked: Array<Boolean>
    private val edgeTo: Array<Int>
    private val visited: Array<Boolean>
    private var cycle: Stack<Int>? = null

    init {
        marked = Array(graph.vertices) { false }
        visited = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { -1 }
        for (v in 0 until graph.vertices) {
            if (!marked[v] && cycle == null)  {
                dfs(graph, v)
            }
        }
    }

    private fun dfs(graph: DGraph, v: Int) {
        marked[v] = true
        visited[v] = true
        graph.adjacent(v).forEach { w ->
            when {
                // short circuit if directed cycle found
                cycle != null -> {
                    return
                }
                // found new vertex
                !marked[w] -> {
                    edgeTo[w] = v
                    dfs(graph, w)
                }
                // trac back directed cycle
                visited[w] -> {
                    cycle = LinkedListStack<Int>().apply {
                        var x = v
                        while (x != w) {
                            push(x)
                            x = edgeTo[x]
                        }
                        push(w)
                        push(v)
                    }
                }
            }
        }
        visited[v] = false
    }

    fun hasCycle(): Boolean  {
        return cycle != null
    }

    fun cycle(): Iterable<Int>? {
        return cycle
    }
}