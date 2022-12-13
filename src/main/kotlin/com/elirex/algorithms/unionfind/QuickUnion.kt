package com.elirex.algorithms.unionfind

class QuickUnion(size: Int) : UnionFind(size) {

    override fun union(p: Int, q: Int) {
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) {
            return
        }
        id[rootP] = rootQ;
        count--
    }

    override fun find(p: Int): Int {
        validate(p)
        var root = p
        while (root != id[root]) {
            root = id[root]
        }
        return root
    }

    override fun connected(p: Int, q: Int): Boolean = find(p) == find(q)

}