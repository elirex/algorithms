package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class ConnectedComponents(
    graph: Graph,
) {
    private val marked: Array<Boolean>
    private val id: Array<Int>
    private val size: Array<Int>
    var count: Int
        private set

    init {
        marked = Array(graph.vertices) { false }
        id = Array(graph.vertices) { 0 }
        size = Array(graph.vertices) { 0 }
        count = 0
        for (v in 0 until graph.vertices) {
            if (!marked[v]) {
                dfs(graph, v)
                count++
            }
        }
    }

    private fun dfs(graph: Graph, v: Int) {
        marked[v] = true
        id[v] = count
        size[count]++
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
    }

    fun size(v: Int): Int {
        validateVertex(v)
        return size[id[v]]
    }

    fun id(v: Int): Int {
        validateVertex(v)
        return id[v]
    }

    fun connected(v: Int, w: Int): Boolean {
        validateVertex(v)
        validateVertex(w)
        return id[v] == id[w]
    }

    fun components(graph: Graph): Array<Array<Int>> {
        val components: Array<Queue<Int>> = Array(count) { LinkedListQueue<Int>() }
        for (v in 0 until graph.vertices) {
            components[id(v)].enqueue(v)
        }
        return Array(components.size) { i ->
            var j = 0
            val arr = Array<Int>(components[i].size()) { 0 }
            components[i].forEach{ v ->
                arr[j++] = v
            }
            arr
        }
    }

    private fun validateVertex(v: Int) {
        val V = marked.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}