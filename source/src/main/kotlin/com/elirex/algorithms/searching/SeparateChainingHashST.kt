package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class SeparateChainingHashST<Key : Comparable<Key>, Value>(
    capacity: Int = INIT_CAPACITY
) {
    var size: Int = 0
        private set
    private var m: Int = capacity
    private var symbolTable: Array<SequentialSearchST<Key, Value>> = Array(m) { i ->
        SequentialSearchST()
    }

    fun get(key: Key): Value? {
        val i = hash(key)
        return symbolTable[i].get(key)
    }

    fun contains(key: Key): Boolean {
        return get(key) != null
    }

    fun put(key: Key, value: Value?) {
        if (value == null) {
            delete(key)
            return
        }
        if (size > 10 * m) {
            return resize(2 * m)
        }
        val i = hash(key)
        if (!symbolTable[i].contains(key)) {
            size++
        }
        symbolTable[i].put(key, value)

    }

    fun delete(key: Key) {
        val i = hash(key)
        if (symbolTable[i].contains(key)) {
            size--
        }
        symbolTable[i].remove(key)
        if (m > INIT_CAPACITY && size <= 2 * m) {
            resize(m / 2)
        }
    }

    fun isEmpty(): Boolean = size == 0

    fun keys(): Iterable<Key> {
        val queue: Queue<Key> = LinkedListQueue<Key>()
        for (i in 0 until m) {
            symbolTable[i].keys().forEach { key ->
                queue.enqueue(key)
            }
        }
        return queue
    }

    private fun resize(chains: Int) {
        val temp = SeparateChainingHashST<Key, Value>(chains)
        for (i in 0 until m) {
            symbolTable[i].keys().forEach { key ->
                temp.put(key, symbolTable[i].get(key))
            }
        }
        m = temp.m
        size = temp.size
        symbolTable = temp.symbolTable
    }

    private fun hash(key: Key): Int {
        var h = key.hashCode()
        h = h xor (h ushr 20 xor (h ushr 12) xor (h ushr 7) xor (h ushr 4))
        return h and m - 1
    }

    private fun hashTextbook(key: Key): Int {
        return (key.hashCode() and 0x7fffffff) % m
    }

    companion object {
        private const val INIT_CAPACITY = 4
    }
}
