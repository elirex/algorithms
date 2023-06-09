package com.elirex.algorithms.graph

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import com.elirex.algorithms.stack.LinkedListStack

class DepthFirstOrder(
    graph: DGraph,
) {

    private val marked: Array<Boolean>
    private val preOrder: Queue<Int>
    private val postOrder: Queue<Int>

    val pre: Iterable<Int>
        get() = preOrder

    val post: Iterable<Int>
        get() = postOrder

    val reversePost: Iterable<Int>
        get() = LinkedListStack<Int>().apply {
            postOrder.forEach { v ->
                push(v)
            }
        }

    init {
        marked = Array(graph.vertices) { false }
        preOrder = LinkedListQueue()
        postOrder = LinkedListQueue()
        for (v in 0 until graph.vertices) {
            if (!marked[v]) {
                dfs(graph, v)
            }
        }
    }

    private fun dfs(graph: DGraph, v: Int) {
        marked[v] = true
        preOrder.enqueue(v)
        graph.adjacent(v).forEach { w ->
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
        postOrder.enqueue(v)
    }

}