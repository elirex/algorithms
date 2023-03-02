package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import java.util.TreeMap

class OrderedST<Key: Comparable<Key>, Value> : SymbolTable<Key, Value> {

    private val st: TreeMap<Key, Value> = TreeMap()

    override fun put(key: Key, value: Value?) {
        if (value == null) {
            remove(key)
        } else {
            st[key] = value
        }
    }

    override fun get(key: Key): Value? = st[key]

    override fun remove(key: Key) {
        st.remove(key)
    }

    override fun contains(key: Key): Boolean {
        return st.containsKey(key)
    }

    override fun isEmpty(): Boolean = size() == 0

    override fun size(): Int = st.size

    override fun size(low: Key, high: Key): Int {
        if (low > high) {
            return 0
        }
        return rank(high) - rank(low) + if (contains(high)) 1 else 0
    }

    override fun min(): Key = st.firstKey()

    override fun max(): Key = st.lastKey()

    override fun select(k: Int): Key {
        if (k < 0 || k >= size()) {
            throw IllegalArgumentException("called select() while invalid argument")
        }
        return st.keys.toList()[k]
    }

    override fun removeMin() {
        remove(min())
    }

    override fun removeMax() {
        remove(max())
    }

    override fun keys(): Iterable<Key> = st.keys.asIterable()

    override fun keys(low: Key, high: Key): Iterable<Key> {
        val queue = LinkedListQueue<Key>()
        if (low > high) return queue
        val keys = st.keys.toList()
        for (i in rank(low) until rank(high)) {
            queue.enqueue(keys[i])
        }
        if (contains(high)) {
            queue.enqueue(keys[rank(high)])
        }
        return queue
    }

    override fun rank(key: Key): Int {
        val keys = st.keys.toList()
        var low = 0
        var high = keys.size - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            val cmp = key.compareTo(keys[mid])
            when {
                cmp > 0 -> low = mid + 1
                cmp < 0 -> high = mid - 1
                else -> return mid
            }
        }
        return low
    }

    override fun ceiling(key: Key): Key {
        return st.ceilingKey(key) ?: throw NoSuchElementException("argument to ceiling() is too large")
    }

    override fun floor(key: Key): Key {
        return st.floorKey(key) ?: throw NoSuchElementException("argument to floor() is too small")
    }
}