package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.unionfind.QuickFind

class KruskalMST(
    graph: EdgeWeightedGraph,
) {

    var weight: Double = 0.0
        private set

    private val mst: Queue<Edge>

    init {
        mst = LinkedListQueue()
        val edges: Array<Edge> = arrayOfNulls<Edge>(graph.edges) as Array<Edge>
        var i = 0
        graph.edges().forEach { e ->
            edges[i++] = e
        }
        edges.sort()
        val uf = QuickFind(graph.vertices)

        i = 0
        while (i < graph.edges && mst.size() < graph.vertices -1) {
            val e = edges[i]
            val v = e.either()
            val w = e.other(v)

            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w)
                mst.enqueue(e)
                weight += e.weight
            }
            i++
        }
    }

    fun edges(): Iterable<Edge> {
        return mst
    }
}