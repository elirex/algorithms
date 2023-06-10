package com.elirex.algorithms.graph

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag

class Graph(
    val vertices: Int
) {

    var edges: Int  = 0
        private set

    private val adj: Array<Bag<Int>>

    init {
        if (vertices < 0) {
            throw IllegalArgumentException("Number of vertices must be >= 0")
        }
        adj = Array(vertices) { LinkedListBag<Int>() }
    }

    fun addEdge(v: Int, w: Int) {
        validateVertex(v)
        validateVertex(w)
        edges++
        adj[v].add(w)
        adj[w].add(v)
    }

    fun adjacent(v: Int): Iterable<Int> {
        validateVertex(v)
        return adj[v]
    }

    fun degree(v: Int): Int {
        validateVertex(v)
        return adj[v].size()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("$vertices vertices, $edges edges \n")
        for (v in 0 until vertices) {
            sb.append("$v: ")
            adj[v].forEach { w ->
                sb.append("$w ")
            }
            if (v < vertices - 1) {
                sb.append('\n')
            }
        }
        return sb.toString()
    }

    private fun validateVertex(v: Int) {
        if (v < 0 || v > vertices) {
            throw IllegalArgumentException("vertex $v is not in 0 to $vertices")
        }
    }
}