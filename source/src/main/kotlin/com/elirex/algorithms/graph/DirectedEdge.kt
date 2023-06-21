package com.elirex.algorithms.graph

data class DirectedEdge(
    private val v: Int,
    private val w: Int,
    val weight: Double,
) {

    init {
        if (v < 0 || w < 0) {
            throw IllegalArgumentException("vertex index must be not non negative integer")
        }
    }

    val from: Int
        get() = v

    val to: Int
        get() = w
}