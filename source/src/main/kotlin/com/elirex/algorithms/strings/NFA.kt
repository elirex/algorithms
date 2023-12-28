package com.elirex.algorithms.strings

import com.elirex.algorithms.bag.Bag
import com.elirex.algorithms.bag.LinkedListBag
import com.elirex.algorithms.graph.DGraph
import com.elirex.algorithms.graph.DirectedDepthFirstSearch
import com.elirex.algorithms.stack.LinkedListStack
import com.elirex.algorithms.stack.Stack

class NFA(private val regexp: String) {
    private val graph: DGraph // Directed Graph of epsilon transitions
    private val m: Int // Number of characters in regular expression

    init {
        m = regexp.length
        graph = DGraph(m + 1)
        val ops: Stack<Int> = LinkedListStack<Int>()
        for (i in 0 until m) {
            var lp: Int = i
            when (regexp[i]) {
                '(', '|' -> {
                    ops.push(i)
                }
                ')' -> {
                    val or = ops.pop()
                    when (regexp[or]) {
                        '|' -> {
                            lp = ops.pop()
                            graph.addEdge(lp, or + 1)
                            graph.addEdge(or, i)
                        }
                        '(' -> {
                            lp = or
                        }
                        else -> {
                            assert(false)
                        }
                    }
                }
            }
            if (i < m -1 && regexp[i + 1] == '*') {
                graph.addEdge(lp, i + 1)
                graph.addEdge(i + 1, lp)
            }
            when (regexp[i]) {
                '(', '*', ')' -> graph.addEdge(i, i + 1)
            }
        }
        if (!ops.isEmpty()) {
            throw IllegalArgumentException("Invalid regular expression")
        }
    }

    fun recognizes(txt: String): Boolean {
        var dfs = DirectedDepthFirstSearch(graph, 0)
        var pc: Bag<Int> = LinkedListBag()
        for (v in 0 until graph.vertices) {
            if (dfs.marked(v)) {
                pc.add(v)
            }
        }
        txt.forEach { c ->
            when (c) {
                '*', '|', '(', ')' -> {
                    throw IllegalArgumentException("text contains the metacharacter '$c'")
                }
            }
            val match: Bag<Int> = LinkedListBag()
            pc.forEach { v ->
                if (v == m) {
                    return@forEach
                }
                when (regexp[v]) {
                    c, '.' -> match.add(v + 1)
                }
            }
            if (match.isEmpty()) {
                return@forEach
            }
            dfs = DirectedDepthFirstSearch(graph, match)
            pc = LinkedListBag<Int>()
            for (v in 0 until graph.vertices) {
                if (dfs.marked(v)) {
                    pc.add(v)
                }
            }
            if (pc.isEmpty()) {
                return false
            }
        }
        pc.forEach { v ->
            if (v == m) {
                return true
            }
        }
        return false
    }
}