package com.elirex.algorithms.graph

class Topological {

    val order: Iterable<Int>?
    private val rank: Array<Int>

    constructor(
        graph: DGraph,
    ) {
        val finder = DirectedCycle(graph)
        rank = Array(graph.vertices) { -1 }
        if (!finder.hasCycle()) {
            val dfs = DepthFirstOrder(graph)
            order = dfs.reversePost
            order.forEachIndexed { i, v ->
                rank[v] = i
            }
        } else {
            order = null
        }
    }

    constructor(
        graph: EdgeWeightedDGraph,
    ) {
        val finder = EdgeWeightedDirectedCycle(graph)
        rank = Array(graph.vertices) { -1 }
        if (!finder.hasCycle) {
            val dfs = DepthFirstOrder(graph)
            order = dfs.reversePost
            order.forEachIndexed { i, v ->
                rank[v] = i
            }
        } else {
            order = null
        }
    }

    fun hasOrder(): Boolean = order != null

    fun rank(v: Int): Int {
        return rank[v]
    }

    private fun validateVertex(v: Int) {
        val V = rank.size - 1
        if (v < 0 || v > V) {
            throw IllegalArgumentException("vertex $v is not in 0 to $V")
        }
    }

}