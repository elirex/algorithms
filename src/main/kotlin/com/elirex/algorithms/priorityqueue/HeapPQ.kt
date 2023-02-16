package com.elirex.algorithms.priorityqueue

import com.elirex.algorithms.extensions.swap
import com.elirex.algorithms.utils.less

class HeapPQ<Key: Comparable<Key>>(
    capacity: Int = 0,
    private val comparator: Comparator<Key>? = null
) : PQ<Key> {

    private var pq: Array<Key>
    private var size: Int

    init {
        pq = arrayOfNulls<Comparable<*>>(capacity + 1) as Array<Key>
        size = 0
    }
    override fun push(key: Key) {
        if (size == pq.size - 1) {
            resize(2 * pq.size)
        }
        pq[++size] = key
        swim(size)
    }

    override fun pop(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Priority queue is empty")
        }
        val v = pq[1]
        pq.swap(1, size--)
        sink(1)
        if ((size > 0) && (size == (pq.size - 1) / 4)) {
            resize(pq.size / 2)
        }
        return v
    }

    override fun peek(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Priority queue is empty")
        }
        return pq[1]
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    private fun swim(k: Int) {
        var i = k
        while (i > 1 && less(pq[i / 2], pq[i], comparator)) {
            pq.swap(i/2, i)
            i /= 2
        }
    }

    private fun sink(k: Int) {
        var i = k
        while (2 * i <= size) {
            var j = 2 * i
            if (j < size && less(pq[j], pq[j + 1], comparator)) {
                j++
            }
            if (!less(pq[i], pq[j], comparator)) {
                break
            }
            pq.swap(i, j)
            i = j
        }
    }

    private fun resize(capacity: Int) {
        val resized: Array<Key> = arrayOfNulls<Comparable<*>>(capacity) as Array<Key>
        for (i in 1 .. size) {
            resized[i] = pq[i]
        }
        pq = resized
    }
}