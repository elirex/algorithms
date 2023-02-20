package com.elirex.algorithms.priorityqueue

import com.elirex.algorithms.utils.less
import com.elirex.algorithms.utils.swap

class UnorderedArrayPQ<Key: Comparable<Key>>(
    capacity: Int,
    private val comparator: Comparator<Key>? = null
) : PQ<Key> {

    private val pq: Array<Key>
    private var size: Int

    init {
        pq = arrayOfNulls<Comparable<*>>(capacity) as Array<Key>
        size = 0
    }

    override fun push(key: Key) {
        pq[size++] = key
    }

    override fun pop(): Key {
        var v = 0;
        for (i in 1 until size) {
            if (less(pq[v], pq[i], comparator)) {
                v = i
            }
        }
        swap(pq, v, size - 1)
        return pq[--size]
    }

    override fun peek(): Key = pq[size - 1]

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size
}