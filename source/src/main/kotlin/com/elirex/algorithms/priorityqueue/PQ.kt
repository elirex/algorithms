package com.elirex.algorithms.priorityqueue

interface PQ<Key> {
    fun push(key: Key)
    fun pop(): Key
    fun peek(): Key
    fun isEmpty(): Boolean
    fun size(): Int
}