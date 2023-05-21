package com.elirex.algorithms.graph

class Biconnected(
    graph: Graph,
) {
    private var count: Int = 0
    private val pre: Array<Int>
    private val low: Array<Int>
    private val articulation: Array<Boolean>

    fun isArticulation(v: Int): Boolean = articulation[v]

    init {
        low = Array(graph.vertices) { -1 }
        pre = Array(graph.vertices) { -1 }
        articulation = Array(graph.vertices) { false }
        for (v in 0 until graph.vertices) {
            if (pre[v] == -1) {
                dfs(graph, v, v)
            }
        }
    }

    private fun dfs(graph: Graph, u: Int, v: Int) {
        var children: Int = 0
        pre[v] = count++
        low[v] = pre[v]
        graph.adjacent(v).forEach { w ->
            if (pre[w] == -1) {
                children++
                dfs(graph, v, w)
                low[v] = Math.min(low[v], low[w])
                if (low[w] >=  pre[v] && u != v) {
                    articulation[v] = true
                }
            } else if (w != u) {
                low[v] = Math.min(low[v], pre[w])
            }
        }
        if (u == v && children > 1) {
            articulation[v] = true
        }
    }
}