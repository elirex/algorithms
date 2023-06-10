package com.elirex.algorithms.queue

import org.jetbrains.annotations.TestOnly

@Suppress("UNCHECKED_CAST")
class ArrayQueue<Item>(
    capacity: Int = DEFAULT_CAPACITY
): Queue<Item> {

    companion object {
        private const val DEFAULT_CAPACITY: Int = 8
    }

    private var array: Array<Item>
    private var head: Int
    private var tail: Int
    private var size: Int

    init {
        array = arrayOfNulls<Any>(capacity) as Array<Item>
        head = 0
        tail = 0
        size = 0
    }

    override fun enqueue(item: Item) {
        if (size == array.size) {
            resize(size * 2)
        }
        array[tail++] = item
        size++
    }

    override fun dequeue(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        val item = array[head++]
        size--
        if (size > 0 && size == array.size / 4) {
            resize(array.size / 2)
        }
        return item
    }

    override fun top(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        return array[head]
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = ArrayIterator(head, size)

    @TestOnly
    internal fun capacity(): Int = array.size

    @TestOnly
    internal fun resize(capacity: Int) {
        val resizedArray: Array<Item> = arrayOfNulls<Any>(capacity) as Array<Item>
        for (i in 0 until size) {
            resizedArray[i] = array[(head + i) % array.size]
        }
        array = resizedArray
        head = 0
        tail = size
    }

    private inner class ArrayIterator(
        private var index: Int,
        private val size: Int
    ) : Iterator<Item> {
        override fun hasNext(): Boolean = index < size

        override fun next(): Item {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            return array[index++]
        }

    }
}