package com.elirex.algorithms.graph

import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class AcyclicShortestPaths(
    graph: EdgeWeightedDGraph,
    source: Int,
) {

    private val distTo: Array<Double>
    private val edgeTo: Array<DirectedEdge?>

    init {
        distTo = Array(graph.vertices) { Double.POSITIVE_INFINITY }
        distTo[source] = 0.0

        edgeTo = Array(graph.vertices) { null }

        validateVertex(source)

        val topological = Topological(graph)
        if (!topological.hasOrder()) {
            throw IllegalArgumentException("Directed is not acyclic")
        }
        topological.order?.forEach { v ->
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
            edgeTo[w] =e
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