package com.elirex.algorithms.graph

class Topological(
    graph: DGraph,
) {

    val order: Iterable<Int>?
    private val rank: Array<Int>

    init {
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