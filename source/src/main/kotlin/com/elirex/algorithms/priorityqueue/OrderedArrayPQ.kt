package com.elirex.algorithms.priorityqueue

import com.elirex.algorithms.utils.less

class OrderedArrayPQ<Key: Comparable<Key>>(
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
        var i = size - 1
        while (i >= 0 && less(key, pq[i], comparator)) {
            pq[i + 1] = pq[i]
            i--
        }
        pq[i + 1] = key
        size++
    }

    override fun pop(): Key {
        return pq[--size]
    }

    override fun peek(): Key {
        return pq[size - 1]
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size
}