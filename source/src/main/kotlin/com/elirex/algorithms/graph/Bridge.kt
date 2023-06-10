package com.elirex.algorithms.graph

class Bridge(
    graph: Graph,
) {
    private var bridges: Int = 0
    private var count: Int = 0
    private val pre: Array<Int>
    private val low: Array<Int>

    val components: Int
        get() = bridges + 1

    init {
        low = Array(graph.vertices) { -1 }
        pre = Array(graph.vertices) { -1 }
        for (v in 0 until graph.vertices) {
            if (pre[v] == -1) {
                dfs(graph, v, v)
            }
        }
    }

    private fun dfs(graph: Graph, u: Int, v: Int) {
        pre[v] = count++
        low[v] = pre[v]
        graph.adjacent(v).forEach { w ->
            if (pre[w] == -1) {
                dfs(graph, v, w)
                low[v] = Math.min(low[v], low[w])
                if (low[w] == pre[w]) {
                    bridges++
                }
            } else if (w != u) {
                low[v] = Math.min(low[v], pre[w])
            }
        }
    }
}