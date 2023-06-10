package com.elirex.algorithms.searching

interface SymbolTable<Key, Value> {
    fun put(key: Key, value: Value?)
    fun get(key: Key): Value?
    fun remove(key: Key)
    fun contains(key: Key): Boolean
    fun isEmpty(): Boolean
    fun size(): Int
    fun min(): Key
    fun max(): Key
    fun floor(key: Key): Key
    fun ceiling(key: Key): Key
    fun rank(key: Key): Int
    fun select(k: Int): Key
    fun removeMin()
    fun removeMax()
    fun size(low: Key, high: Key): Int
    fun keys(): Iterable<Key>
    fun keys(low: Key, high: Key): Iterable<Key>
}