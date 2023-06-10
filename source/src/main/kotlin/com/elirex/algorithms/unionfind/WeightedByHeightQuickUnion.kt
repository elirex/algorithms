package com.elirex.algorithms.unionfind

class WeightedByHeightQuickUnion(size: Int) : UnionFind(size) {
    private val height: IntArray

    init {
        height = IntArray(size) {0}
    }

    override fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) {
            return
        }
        if (height[rootP] < height[rootQ]) {
            id[rootP] = rootQ
        } else if (height[rootP] > height[rootQ]) {
            id[rootQ] = rootP
        } else {
            id[rootQ] = rootP
            height[rootP]++
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