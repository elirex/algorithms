package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class LinearProbingHashST<Key : Comparable<Key>, Value>(
    capacity: Int = INIT_CAPACITY
) {

    var size: Int = 0
        private set
    private var m: Int = capacity
    private var keys: Array<Key?> = arrayOfNulls<Comparable<*>>(m) as Array<Key?>
    private var values: Array<Value?> = arrayOfNulls<Any>(m) as Array<Value?>

    fun isEmpty(): Boolean = size == 0

    fun contains(key: Key): Boolean = get(key) != null

    fun get(key: Key): Value? {
        var i = hash(key)
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                return values[i]
            }
            i = (i + 1) % m
        }
        return null
    }

    fun put(key: Key, value: Value?) {
        if (value == null) {
            delete(key)
            return
        }

        if (size >= m / 2) {
            resize(2 * m)
        }

        var i = hash(key)
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                values[i] = value
                return
            }
            i = (i + 1) % m
        }
        keys[i] = key
        values[i] = value
        size++
    }


    fun delete(key: Key) {
        if (!contains(key)) {
            return
        }

        var i = hash(key)
        while (!key.equals(keys[i])) {
            i = (i + 1) % m
        }
        keys[i] = null
        values[i] = null

        i = (i + 1) % m
        while (keys[i] != null) {
            val refreshKey = keys[i]
            val refreshValue = values[i]
            keys[i] == null
            values[i] == null
            size--
            if (refreshKey != null) {
                put(refreshKey, refreshValue)
            }
            i = (i + 1) % m
        }

        size--
        if (size > 0 && size < m / 8) {
            resize(m / 2)
        }
    }

    fun keys(): Iterable<Key> {
        val queue: Queue<Key> = LinkedListQueue<Key>()
        keys.forEach { key ->
            if (key != null) {
                queue.enqueue(key)
            }
        }
        return queue
    }

    private fun resize(capacity: Int) {
        val temp = LinearProbingHashST<Key, Value>(capacity)
        for (i in 0 until m) {
            var key: Key? = keys[i]
            if (key != null) {
                temp.put(key, values[i])
            }
        }
        keys = temp.keys
        values = temp.values
        m = temp.m
    }

    private fun hash(key: Key): Int {
        var h = key.hashCode()
        h = h xor (h ushr 20 xor (h ushr 12) xor (h ushr 7) xor (h ushr 4))
        return h and m - 1
    }

    companion object {
        private const val INIT_CAPACITY = 4
    }
}