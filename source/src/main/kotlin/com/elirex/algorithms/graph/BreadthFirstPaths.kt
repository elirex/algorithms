package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.stack.LinkedListStack

class BreadthFirstPaths(
    graph: Graph,
    private val source: Int,
) {
    private val marked: Array<Boolean>
    private val edgeTo: Array<Int>

    init {
        marked = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { -1 }
        validateVertex(source)
        bfs(graph, source)
    }

    private fun bfs(graph: Graph, s: Int) {
        val queue: Queue<Int> = LinkedListQueue()
        marked[s] = true
        queue.enqueue(s)

        while (!queue.isEmpty()) {
            val v = queue.dequeue()
            graph.adjacent(v).forEach { w ->
                if (marked[w]) {
                    return@forEach
                }
                edgeTo[w] = v
                marked[w] = true
                queue.enqueue(w)
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