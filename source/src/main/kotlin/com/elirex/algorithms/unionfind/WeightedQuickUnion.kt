package com.elirex.algorithms.unionfind

class WeightedQuickUnion(size: Int) : UnionFind(size) {
    private val rank: IntArray

    init {
        rank = IntArray(size) { 1 }
    }

    override fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) {
            return
        }

        if (rank[rootP] < rank[rootQ]) {
            id[rootP] = rootQ
            rank[rootQ] += rank[p]
        } else {
            id[rootQ] = rootP
            rank[rootP] += rank[rootQ]
        }
        count--
    }

    override fun find(p: Int): Int {
        validate(p)
        var parent = p;
        while (parent != id[parent]) {
            parent = id[parent]
        }
        return parent
    }

    override fun connected(p: Int, q: Int): Boolean = find(p) == find(q)
}