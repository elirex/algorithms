package com.elirex.algorithms.graph

import com.elirex.algorithms.priorityqueue.IndexMinHeapPQ
import com.elirex.algorithms.queue.LinkedListQueue
import kotlin.math.roundToInt

class EagerPrimMST(
    graph: EdgeWeightedGraph,
) {

    private val edgeTo: Array<Edge?>
    private val distTo: Array<Double>
    private val marked: Array<Boolean>
    private val pq: IndexMinHeapPQ<Double>
    val weight: Double
        get() = (edgeTo.sumOf { it?.weight ?: 0.0 } * 100.0).roundToInt() / 100.0

    init {
        edgeTo = arrayOfNulls(graph.vertices)
        distTo = Array(graph.vertices) { Double.POSITIVE_INFINITY }
        marked = Array(graph.vertices) { false }
        pq = IndexMinHeapPQ(graph.vertices)
        for (v in 0 until graph.vertices) {
            if (!marked[v]) {
                prim(graph, v)
            }
        }
    }

    private fun prim(graph: EdgeWeightedGraph, s: Int) {
        distTo[s] = 0.0
        pq.insert(s, distTo[s])
        while (!pq.isEmpty()) {
            val v = pq.removeMin()
            visit(graph, v)
        }
    }

    private fun visit(graph: EdgeWeightedGraph, v: Int) {
        marked[v] = true
        graph.adjacent(v).forEach { edage ->
            val w = edage.other(v)
            if (marked[w]) {
                return@forEach
            }
            if (edage.weight < distTo[w]) {
                distTo[w] = edage.weight
                edgeTo[w] = edage
                if (pq.contains(w)) {
                    pq.decreaseKey(w, distTo[w])
                } else {
                    pq.insert(w, distTo[w])
                }
            }
        }
    }

    fun edges(): Iterable<Edge> {
        val mst = LinkedListQueue<Edge>()
        edgeTo.forEach { edge ->
            edge?.let {
                mst.enqueue(it)
            }
        }
        return mst
    }
}