package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class BreadthFirstDirectedPaths(
    graph: DGraph,
    private val source: Int,
) {

    private val marked: Array<Boolean>
    private val edgeTo: Array<Int>
    private val distTo: Array<Int>

    init {
        marked = Array(graph.vertices) { false }
        edgeTo = Array(graph.vertices) { -1 }
        distTo = Array(graph.vertices) { -1 }
        validateVertex(source)
        bfs(graph, source)
    }

    private fun bfs(graph: DGraph, s: Int) {
        val queue: Queue<Int> = LinkedListQueue()
        marked[s] = true
        distTo[s] = 0
        queue.enqueue(s)
        while (!queue.isEmpty()) {
            val v = queue.dequeue()
            graph.adjacent(v).forEach { w ->
                if (!marked[w]) {
                    edgeTo[w] = v
                    distTo[w] = distTo[v] + 1
                    marked[w] = true
                    queue.enqueue(w)
                }
            }
        }
    }

    fun hasPathTo(v: Int): Boolean {
        validateVertex(v)
        return marked[v]
    }

    fun distTo(v: Int): Int {
        validateVertex(v)
        return distTo[v]
    }

    fun pathTo(v: Int): Iterable<Int>? {
        validateVertex(v)
        if (!hasPathTo(v)) {
            return null
        }
        val path: Stack<Int> = LinkedListStack()
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