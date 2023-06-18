package com.elirex.algorithms.graph

import com.elirex.algorithms.priorityqueue.HeapPQ
import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class LazyPrimMST(
    graph: EdgeWeightedGraph,
) {

    private val mst: Queue<Edge>
    private val masked: Array<Boolean>
    private val pq: HeapPQ<Edge>
    var weight: Double = 0.0
        private set

    init {
        mst = LinkedListQueue<Edge>()
        masked = Array(graph.vertices) { false }
        pq = HeapPQ<Edge> { e1, e2 -> e2.compareTo(e1) }
        for (v in 0 until graph.vertices) {
            if (!masked[v]) {
                prim(graph, v)
            }
        }
    }

    private fun prim(graph: EdgeWeightedGraph, s: Int) {
        visit(graph, s)
        while (!pq.isEmpty())  {
            val edge = pq.pop()
            val v = edge.either()
            val w = edge.other(v)
            if (masked[v] && masked[w]) {
                println("Ineligible $edge")
                continue
            }
            mst.enqueue(edge)
            weight += edge.weight
            if (!masked[v]) {
                visit(graph, v)
            }
            if (!masked[w]) {
                visit(graph, w)
            }
        }
    }

    private fun visit(graph: EdgeWeightedGraph, v: Int) {
        masked[v] = true
        graph.adjacent(v).forEach { edge ->
            val w = edge.other(v)
            if (!masked[w]) {
                pq.push(edge)
            }
        }
    }

    fun edges(): Iterable<Edge> {
        return mst
    }

}