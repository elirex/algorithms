package com.elirex.algorithms.searching

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
        TODO("Not yet implemented")
    }

    override fun min(): Key = st.firstKey()

    override fun max(): Key = st.lastKey()

    override fun select(k: Int): Key {
        TODO("Not yet implemented")
    }

    override fun removeMin() {
        remove(min())
    }

    override fun removeMax() {
        remove(max())
    }

    override fun keys(): Iterable<Key> = st.keys.asIterable()

    override fun keys(low: Key, high: Key): Iterable<Key> {
        TODO("Not yet implemented")
    }

    override fun rank(key: Key): Int {
        TODO("Not yet implemented")
    }

    override fun ceiling(key: Key): Key {
        return st.ceilingKey(key) ?: throw NoSuchElementException("argument to ceiling() is too large")
    }

    override fun floor(key: Key): Key {
        return st.floorKey(key) ?: throw NoSuchElementException("argument to floor() is too small")
    }
}