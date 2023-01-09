package com.elirex.algorithms.bag


class LinkedListBag<Item> : Bag<Item> {

    private var first: Node<Item>? = null
    private var size: Int = 0

    override fun add(item: Item) {
        first = Node(item, first)
        size++
    }

    override fun isEmpty(): Boolean = first == null

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = LinkedListIterator()

    private data class Node<Item>(
        val item: Item,
        var next: Node<Item>? = null
    )

    private inner class LinkedListIterator : Iterator<Item> {
        private var current: Node<Item>? = first

        override fun hasNext(): Boolean = current != null

        override fun next(): Item {
            if (hasNext().not()) throw NoSuchElementException()
            val item = current?.item ?: throw NoSuchElementException()
            current = current?.next
            return item
        }
    }

}