package com.elirex.algorithms.graph

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag

class EdgeWeightedDGraph(
    val vertices: Int,
) {

    var edges: Int = 0
        private set

    private val indegree: Array<Int>
    private val adj: Array<Bag<DirectedEdge>>

    init {
        if (vertices < 0) {
            throw IllegalArgumentException("Number of vertices must be >= 0")
        }
        indegree = Array(vertices) { 0 }
        adj = Array(vertices) { LinkedListBag() }
    }

    fun addEdge(e: DirectedEdge) {
        val v = e.from
        val w = e.to
        validateVertex(v)
        validateVertex(w)
        adj[v].add(e)
        indegree[w]++
        edges++
    }

    fun adjacent(v: Int): Iterable<DirectedEdge> {
        validateVertex(v)
        return adj[v]
    }

    fun indegree(v: Int): Int {
        validateVertex(v)
        return indegree[v]
    }

    fun outdegree(v: Int): Int {
        validateVertex(v)
        return adj[v].size()
    }

    fun edges(): Iterable<DirectedEdge> {
        val list = LinkedListBag<DirectedEdge>()
        for (v in 0 until vertices) {
            adjacent(v).forEach { e ->
                list.add(e)
            }
        }
        return list
    }

    private fun validateVertex(v: Int) {
        if (v < 0 || v > vertices) {
            throw IllegalArgumentException("vertex $v is not in 0 to $vertices")
        }
    }
}