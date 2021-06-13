package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/12.
 */
class ArrayQueue<Item> : Queue<Item> {

    private var items: Array<Item?> = arrayOfNulls<Any>(2) as Array<Item?>
    private var size: Int = 0
    private var first: Int = 0
    private var last: Int = 0

    override fun peek(): Item? {
        if (isEmpty()) return null
        return items[first]
    }

    override fun enqueue(item: Item) {
        if (size == items.size) resize(2 * items.size)
        items[last++] = item
        size++
        if (last == items.size) last = 0
    }

    override fun dequeue(): Item? {
        if (isEmpty()) return null
        val item = items[first]
        items[first] = null
        size--
        first++
        if (first == items.size) first = 0
        if (size >= 0 && size == items.size / 4) resize(items.size / 2)
        return item
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = ArrayIterator()

    private fun resize(capacity: Int) {
        assert(capacity >= size)
        val temp: Array<Item?> = arrayOfNulls<Any>(capacity) as Array<Item?>
        for (i in 0 until size) {
            temp[i] = items[(first + i) % items.size]
        }
        items = temp
        first = 0
        last = size
    }

    private inner class ArrayIterator : Iterator<Item> {
        private var i: Int = 0

        override fun hasNext(): Boolean = i < size

        override fun next(): Item {
            if (hasNext().not()) throw NoSuchElementException()
            val item = items[(first + i) % items.size] ?: throw NoSuchElementException()
            i++
            return item
        }
    }

}

fun main(args: Array<String>) {
    val queue: Queue<String> = ArrayQueue()
    queue.enqueue("A")
    queue.enqueue("B")
    queue.enqueue("C")
    queue.enqueue("D")
    queue.enqueue("E")
    queue.enqueue("F")
    for (element: String in queue) {
        println(element)
    }

    println("dequeue: ${queue.dequeue()}")
    println("peek: ${queue.peek()}")
}