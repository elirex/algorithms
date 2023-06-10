package com.elirex.algorithms.queue

interface Queue<Item> : Iterable<Item> {

    fun enqueue(item: Item)

    @Throws(NoSuchElementException::class)
    fun dequeue(): Item

    @Throws(NoSuchElementException::class)
    fun top(): Item

    fun isEmpty(): Boolean

    fun size(): Int

}