package com.elirex.algorithms.queue

class LinkedListQueue<Item> : Queue<Item> {

    private var head: Node<Item>? = null
    private var tail: Node<Item>? = null
    private var size: Int = 0

    override fun enqueue(item: Item) {
        val node = Node(item)
        if (isEmpty()) {
            head = node
            tail = head
        } else {
            tail!!.next = node
            tail = node
        }
        size++
    }

    override fun dequeue(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        val item = head!!.item
        head = head!!.next
        size--
        if (isEmpty()) {
            tail = null
        }
        return item
    }

    override fun top(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        return head!!.item
    }

    override fun isEmpty(): Boolean = head == null

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = LinkedListIterator(head)

    private data class Node<Item>(
        val item: Item,
        var next: Node<Item>? = null
    )

    private inner class LinkedListIterator(
        private var current: Node<Item>?
    ): Iterator<Item> {
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