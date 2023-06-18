package com.elirex.algorithms.graph

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag

class EdgeWeightGraph(
    val vertices: Int,
) {
    private var edges: Int  = 0
    private val adj: Array<Bag<Edge>>

    init {
        if (vertices < 0) {
            throw IllegalArgumentException("Number of vertices must be >= 0")
        }
        adj = Array(vertices) { LinkedListBag() }
    }

    fun addEdge(e: Edge) {
        val v = e.either()
        val w = e.other(v)
        validateVertex(v)
        validateVertex(w)
        adj[v].add(e)
        adj[w].add(e);
        edges++
    }

    fun adjacent(v: Int): Iterable<Edge> {
        validateVertex(v)
        return adj[v]
    }

    fun degree(v: Int): Int {
        validateVertex(v)
        return adj[v].size()
    }

    fun edges(): Iterable<Edge> {
        val list = LinkedListBag<Edge>()
        for (v in 0 until vertices) {
            var selfLoops = 0
            adjacent(v).forEach { e ->
                val w = e.other(v)
                when {
                    w > v -> list.add(e)
                    w == v -> {
                        if (selfLoops % 2 == 0) {
                            list.add(e)
                        }
                        selfLoops++
                    }
                }
            }
        }
        return list
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(vertices)
            .append(" ")
            .append(edges)
            .append('\n')
        for (v in 0 until vertices) {
           sb.append(v)
               .append(':')
               .append(' ')
            adjacent(v).forEach { e ->
                sb.append(e).append(' ')
            }
            sb.append('\n')
        }
        return sb.toString()
    }


    private fun validateVertex(v: Int) {
        if (v < 0 || v > vertices) {
            throw IllegalArgumentException("vertex $v is not in 0 to $vertices")
        }
    }
}