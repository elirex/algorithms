package com.elirex.algorithms.stack

class LinkedListStack<Item> : Stack<Item> {

    private var first: Node<Item>? = null
    private var size: Int = 0

    override fun push(item: Item) {
        first = Node(item, first)
        size++
    }

    override fun pop(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
        val item = first!!.item
        first = first!!.next
        return item
    }

    override fun peek(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
        return first!!.item
    }

    override fun isEmpty(): Boolean = first == null

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = LinkedListIterator(first)

    private data class Node<Item>(
        val item: Item,
        val next: Node<Item>? = null
    )

    private inner class LinkedListIterator(
        private var current: Node<Item>?
    ) : Iterator<Item> {

        override fun hasNext(): Boolean = current != null


        override fun next(): Item {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val item = current!!.item
            current = current!!.next
            return item
        }

    }
}