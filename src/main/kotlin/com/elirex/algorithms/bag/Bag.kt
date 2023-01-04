package com.elirex.algorithms.bag

interface Bag<Item> : Iterable<Item> {
    fun add(item: Item)
    fun isEmpty(): Boolean
    fun size(): Int
}