package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.stack.LinkedListStack

class BellmanFordSP(
    graph: EdgeWeightedDGraph,
    source: Int,
) {

    private val distTo: Array<Double>
    private val edgeTo: Array<DirectedEdge?>
    private val onQueue: Array<Boolean>
    private val queue: Queue<Int>
    private var cycle: Iterable<DirectedEdge>? = null
    private var cost: Int = 0

    init {
        distTo = Array(graph.vertices) { Double.POSITIVE_INFINITY }
        distTo[source] = 0.0
        edgeTo = Array(graph.vertices) { null }
        onQueue = Array(graph.vertices) { false }

        queue = LinkedListQueue()
        queue.enqueue(source)
        onQueue[source] = true
        while (!queue.isEmpty() && !hasNegativeCycle) {
            val v = queue.dequeue()
            onQueue[v] = false
            relax(graph, v)
        }
    }

    private fun relax(graph: EdgeWeightedDGraph, v: Int) {
        graph.adjacent(v).forEach { edge ->
            val w = edge.to
            if (distTo[w] > distTo[v] + edge.weight + EPSILON) {
                distTo[w] = distTo[v] + edge.weight
                edgeTo[w] = edge
                if (!onQueue[w]) {
                    queue.enqueue(w)
                    onQueue[w] = true
                }
            }
            if (++cost % graph.vertices == 0) {
                findNegativeCycle()
                if (hasNegativeCycle) {
                    return
                }
            }
        }
    }

    val hasNegativeCycle: Boolean
        get() = cycle != null

    val negativeCycle: Iterable<DirectedEdge>?
        get() = cycle

    fun distanceTo(v: Int): Double {
        validateVertex(v)
        if (hasNegativeCycle) {
            throw UnsupportedOperationException("Negative cost cycle exists")
        }
        return distTo[v]
    }

    fun hasPathTo(v: Int): Boolean {
        validateVertex(v)
        return distTo[v] < Double.POSITIVE_INFINITY
    }

    fun pathTo(v: Int): Iterable<DirectedEdge>? {
        validateVertex(v)
        if (hasNegativeCycle) {
            throw UnsupportedOperationException("Negative cost cycle exists")
        }
        if (!hasPathTo(v)) {
            return null
        }
        val path = LinkedListStack<DirectedEdge>()
        var e = edgeTo[v]
        while (e != null) {
            path.push(e)
            e = edgeTo[e.from]
        }
        return path
    }

    private fun findNegativeCycle() {
        val V = edgeTo.size
        val spt = EdgeWeightedDGraph(V)
        for (v in 0 until V) {
            edgeTo[v]?.let { e ->
                spt.addEdge(e)
            }
        }
        val finder = EdgeWeightedDirectedCycle(spt)
        cycle = finder.cycle
    }

    private fun validateVertex(v: Int) {
        val V = distTo.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }

    companion object {
        private const val EPSILON: Double = 1E-14
    }
}