package com.elirex.algorithms.graph

import com.elirex.algorithms.priorityqueue.IndexMinHeapPQ
import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class DijkstraShortestPaths(
    graph: EdgeWeightedDGraph,
    source: Int,
) {

    private val distTo: Array<Double>
    private val edgeTo: Array<DirectedEdge?>
    private val pq: IndexMinHeapPQ<Double>

    init {
        graph.edges().forEach { e ->
            if (e.weight < 0) {
                throw IllegalArgumentException("the weight of edge $e is negative")
            }
        }

        distTo = Array(graph.vertices) { Double.POSITIVE_INFINITY }
        distTo[source] = 0.0

        edgeTo = Array(graph.vertices) { null }

        validateVertex(source)
        pq = IndexMinHeapPQ(graph.vertices)
        pq.insert(source, distTo[source])

        while (!pq.isEmpty()) {
            val v = pq.removeMin()
            graph.adjacent(v).forEach { e ->
                relax(e)
            }
        }
    }

    private fun relax(e: DirectedEdge) {
        val v = e.from
        val w = e.to
        if (distTo[w] > distTo[v] + e.weight) {
            distTo[w] = distTo[v] + e.weight
            edgeTo[w] = e
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w])
            } else {
                pq.insert(w, distTo[w])
            }
        }
    }

    fun distanceTo(v: Int): Double {
        validateVertex(v)
        return distTo[v]
    }

    fun hasPathTo(v: Int): Boolean {
        validateVertex(v)
        return distTo[v] < Double.POSITIVE_INFINITY
    }

    fun pathTo(v: Int): Iterable<DirectedEdge>? {
        validateVertex(v)
        if (!hasPathTo(v)) {
            return null
        }
        val path: Stack<DirectedEdge> = LinkedListStack()
        var e = edgeTo[v]
        while (e != null) {
            path.push(e)
            e = edgeTo[e.from]
        }
        return path
    }

    private fun validateVertex(v: Int) {
        val V = distTo.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }
}