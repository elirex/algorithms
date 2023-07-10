package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class EdgeWeightedDirectedCycle(
    graph: EdgeWeightedDGraph,
) {

    private val marked: Array<Boolean>
    private val edgeTo: Array<DirectedEdge?>
    private val onStack: Array<Boolean>
    private var _cycle: Stack<DirectedEdge>? = null

    val hasCycle: Boolean
        get() = _cycle != null
    val cycle: Iterable<DirectedEdge>?
        get() = _cycle

    init {
        marked = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { null }
        onStack = Array(graph.vertices) { false }
        for (v in 0 until graph.vertices) {
            if (!marked[v]) {
                dfs(graph, v)
            }
        }
    }

    private fun dfs(graph: EdgeWeightedDGraph, v: Int) {
        onStack[v] = true
        marked[v] = true
        graph.adjacent(v).forEach { e ->
            val w = e.to
            if (_cycle != null) {
                return
            } else if (!marked[w]) {
                edgeTo[w] = e
                dfs(graph, w)
            } else if (onStack[w]) {
                _cycle = LinkedListStack<DirectedEdge>().apply {
                    var temp: DirectedEdge = e
                    while (temp.from != w) {
                        push(temp)
                        temp = edgeTo[temp.from] ?: break
                    }
                    push(temp)
                }
                return
            }
        }
        onStack[v] = false
    }

}