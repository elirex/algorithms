package com.elirex.algorithms.priorityqueue

import com.elirex.algorithms.extensions.swap
import com.elirex.algorithms.utils.greater

class IndexMinHeapPQ<Key: Comparable<Key>>(
    private val capacity: Int = 0,
) {

    var size: Int = 0
        private set
    private val pq: Array<Int>
    private val qp: Array<Int>
    private val keys: Array<Key?>

    init {
        if (capacity < 0) {
            throw IllegalArgumentException()
        }
        keys = arrayOfNulls<Comparable<*>>(capacity + 1) as Array<Key?>
        pq = Array(capacity + 1) { 0 }
        qp = Array(capacity + 1) { -1 }
    }

    fun isEmpty(): Boolean = size == 0

    fun contains(i: Int): Boolean {
        validateIndex(i)
        return qp[i] != -1
    }

    fun insert(i: Int, key: Key) {
        validateIndex(i)
        if (contains(i)) {
            throw IllegalArgumentException("index is already in the priority queue")
        }
        size++
        qp[i] = size
        pq[size] = i;
        keys[i] = key
        swin(size);
    }

    fun minIndex(): Int {
        if (isEmpty()) {
            throw NoSuchElementException("Priority queue is empty")
        }
        return pq[1]
    }

    fun minKey(): Key = keys[minIndex()]!!

    fun removeMin(): Int {
        if (isEmpty()) {
            throw NoSuchElementException("Priority queue is empty")
        }
        val v = pq[1]
        swap(1, size--)
        sink(1)
        qp[v] = -1
        keys[v] = null
        pq[size + 1] = -1
        return v
    }

    fun keyOf(i: Int): Key {
        validateIndex(i)
        if (!contains(i)) {
            throw NoSuchElementException("index is not in the priority queue.")
        } else {
            return keys[i]!!
        }
    }

    fun changeKey(i: Int, key: Key) {
        validateIndex(i)
        if (!contains(i)) {
            throw NoSuchElementException("index is not in the priority queue.")
        }
        keys[i] = key
        swin(qp[i])
        sink(qp[i])
    }

    fun increaseKey(i: Int, key: Key) {
        validateIndex(i)
        if (!contains(i)) {
            throw NoSuchElementException("index is not in the priority queue.")
        }
        val cmp = keys[i]!!.compareTo(key)
        when {
            cmp == 0 -> throw IllegalArgumentException("a key is equal to the key in the priority queue")
            cmp > 0 -> throw  IllegalArgumentException("a key strictly less than the key in the priority queue")
            else -> {
                keys[i] = key
                sink(qp[i])
            }
        }
    }

    fun decreaseKey(i: Int, key: Key) {
        validateIndex(i)
        if (!contains(i)) {
            throw NoSuchElementException("index is not in the priority queue.")
        }
        val cmp = keys[i]!!.compareTo(key)
        when {
            cmp == 0 -> throw IllegalArgumentException("a key is equal to the key in the priority queue")
            cmp < 0 -> throw  IllegalArgumentException("a key strictly greater than the key in the priority queue")
            else -> {
                keys[i] = key
                swin(qp[i])
            }
        }
    }

    fun remove(i: Int) {
        validateIndex(i)
        if (!contains(i)) {
            throw NoSuchElementException("index is not in the priority queue.")
        }
        val index = qp[i]
        swap(index, size--)
        swin(index)
        sink(index)
        keys[i] = null
        qp[i] = -1
    }


    private fun swin(k: Int) {
        var i = k
        while (i > 1 && greater(keys[pq[i / 2]]!!, keys[pq[i]]!!)) {
            swap(i, i / 2)
            i /= 2
        }
    }

    private fun sink(k: Int) {
        var i = k
        while (2 * i <= size) {
            var j = 2 * i
            if (j < size && greater(keys[pq[j]]!!, keys[pq[j + 1]]!!)) {
                j++
            }
            if (!greater(keys[pq[i]]!!, keys[pq[j]]!!)) {
                break
            }
            swap(i, j)
            i = j
        }
    }

    private fun swap(i: Int, j: Int) {
        pq.swap(i, j)
        qp[pq[i]]  = i
        qp[pq[j]] = j
    }

    private fun validateIndex(i: Int) {
        if (i < 0 || i >= capacity) {
            throw IllegalArgumentException("index must be in 0 ~ $capacity")
        }
    }

}