package com.elirex.algorithms.unionfind

class QuickFind(size: Int) : UnionFind(size) {

    override fun union(p: Int, q: Int) {
        validate(p)
        validate(q)
        val pID = find(p)
        val qID = find(q)

        if (pID == qID) {
            return
        }
        for (i: Int in 0 until id.size) {
            if (id[i] == pID) {
                id[i] = qID
            }
        }
        count--
    }

    override fun find(p: Int): Int {
        validate(p)
        return id[p]
    }

    override fun connected(p: Int, q: Int): Boolean {
        validate(p)
        validate(q)
        return id[p] == id[q]
    }
}