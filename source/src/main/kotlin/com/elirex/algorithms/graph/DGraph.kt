package com.elirex.algorithms.graph

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag

class DGraph(
    val vertices: Int
) {
    var edges: Int = 0
        private set
    private val adj: Array<Bag<Int>>
    private val indegree: Array<Int>

    init {
        if (vertices < 0) {
            throw IllegalArgumentException("number of vertices must be not negative")
        }
        indegree = Array(vertices) { 0 }
        adj = Array(vertices) { LinkedListBag() }
    }

    fun addEdge(v: Int, w: Int) {
        validateVertex(v)
        validateVertex(w)
        adj[v].add(w)
        indegree[w]++
        edges++
    }

    fun adjacent(v: Int): Iterable<Int> {
        validateVertex(v)
        return adj[v]
    }

    fun outdegree(v: Int): Int {
        validateVertex(v)
        return adj[v].size()
    }

    fun indegree(v: Int): Int {
        validateVertex(v)
        return indegree[v]
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