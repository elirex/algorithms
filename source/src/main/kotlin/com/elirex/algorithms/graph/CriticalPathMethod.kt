package com.elirex.algorithms.graph

class CriticalPathMethod(
    graph: EdgeWeightedDGraph,
    source: Int,
) {

    private val lp: AcyclicLongestPaths

    init {
        lp = AcyclicLongestPaths(graph, source)
    }

    fun distanceTo(v: Int): Double = lp.distanceTo(v)

}