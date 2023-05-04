package com.elirex.algorithms.graph

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag

class UndirectedGraph(
    val vertices: Int
) {

    var edges: Int  = 0
        private set

    private var adj: Array<Bag<Int>>

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

    fun verticesAdjacent(v: Int): Iterable<Int> {
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

fun main() {
    val g = UndirectedGraph(13).apply {
        addEdge(0, 1)
        addEdge(0, 2)
        addEdge(0, 5)
        addEdge(0, 6)
        addEdge(3, 4)
        addEdge(3, 5)
        addEdge(4, 5)
        addEdge(4, 6)
        addEdge(7, 8)
        addEdge(9, 10)
        addEdge(9, 11)
        addEdge(9, 12)
        addEdge(11, 12)
    }
    println(g)


}