package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class BreadthFirstSearch(
    graph: Graph,
    source: Int,
) {

    private val marked: Array<Boolean>
    var count: Int
        private set

    init {
        marked = Array(graph.vertices) { false }
        count = 0
        validateVertex(source)
        bfs(graph, source)
    }

    private fun bfs(graph: Graph, s: Int) {
        val queue: Queue<Int> = LinkedListQueue<Int>()
        marked[s] = true
        count++
        queue.enqueue(s)
        while (!queue.isEmpty()) {
            val v = queue.dequeue()
            graph.adjacent(v).forEach { w ->
                if (!marked(w)) {
                    marked[w] = true
                    count++
                    queue.enqueue(w)
                }
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