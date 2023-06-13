package com.elirex.algorithms.graph

data class Edge(
    private val v: Int,
    private val w: Int,
    val weight: Double,
): Comparable<Edge> {

    init {
        if (v < 0 || w < 0) {
            throw IllegalArgumentException("vertex index must be not non negative integer")
        }
        if (weight.isNaN()) {
            throw IllegalArgumentException("weight is not a number")
        }
    }

    fun either(): Int = v

    fun other(vertex: Int): Int {
        return when (vertex) {
            v -> w
            w -> v
            else -> throw IllegalArgumentException("Illegal endpoint")
        }
    }

    override fun compareTo(other: Edge): Int {
        return weight.compareTo(other.weight)
    }

}