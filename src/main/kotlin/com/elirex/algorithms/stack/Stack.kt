package com.elirex.algorithms.stack

interface Stack<Item> : Iterable<Item> {

    fun push(item: Item)

    @Throws(NoSuchElementException::class)
    fun pop(): Item

    @Throws(NoSuchElementException::class)
    fun peek(): Item

    fun isEmpty(): Boolean

    fun size(): Int
}