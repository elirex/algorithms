package com.elirex.algorithms.unionfind

abstract class UnionFind(
    size: Int
) {
    /**
     * Represents the number of sets
     */
    protected var count: Int
        private set
    protected var id: IntArray

    init {
        count = size
        id = IntArray(size) {
            i -> i
        }
    }
    
    abstract fun union(p: Int, q: Int)

    abstract fun find(p: Int): Int

    abstract fun connected(p: Int, q: Int): Boolean

    protected fun validate(p: Int) {
        if (p < 0 || p >= id.size) {
            throw IllegalArgumentException("index $p is not between 0 and ${id.size - 1}")
        }
    }
}