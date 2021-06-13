package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/13.
 */
class LinkedQueue<Item> : Queue<Item> {

    private var first: Node? = null
    private var last: Node? = null
    private var size: Int = 0

    override fun enqueue(item: Item) {
        val node = Node(item)
        last?.next = node
        last = node
        if (isEmpty()) first = last
        size++
    }

    override fun dequeue(): Item? {
        val item: Item? = first?.item
        first = first?.next
        size--
        if (isEmpty()) last = null
        return item
    }

    override fun isEmpty(): Boolean = first == null

    override fun size(): Int = size

    override fun peek(): Item? {
        if (isEmpty()) return null
        return first?.item
    }

    override fun iterator(): Iterator<Item> = LinkedIterator()

    private inner class LinkedIterator : Iterator<Item> {

        private var current: Node? = first
        override fun hasNext(): Boolean = current != null

        override fun next(): Item {
            if (hasNext().not()) throw NoSuchElementException()
            val item = current?.item ?: throw NoSuchElementException()
            current = current?.next
            return item
        }

    }

    private inner class Node(val item: Item, var next: Node? = null)

}

fun main(args: Array<String>) {
    val queue: Queue<String> = LinkedQueue()
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