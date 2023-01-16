package com.elirex.algorithms.stack

import org.jetbrains.annotations.TestOnly

@Suppress("UNCHECKED_CAST")
class ArrayStack<Item>(
    capacity: Int = DEFAULT_CAPACITY
) : Stack<Item> {

    companion object {
        private const val DEFAULT_CAPACITY: Int = 8
    }

    private var array: Array<Item>
    private var size: Int

    init {
        array = arrayOfNulls<Any>(capacity) as Array<Item>
        size = 0
    }

    override fun push(item: Item) {
        if (size == array.size) {
            resize(size * 2)
        }
        array[size++] = item
    }

    override fun pop(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
        val item = array[--size]
        if (size > 0 && size > DEFAULT_CAPACITY && size == array.size / 4) {
            resize(size / 2)
        }
        return item
    }

    override fun peek(): Item {
        if (isEmpty()) {
            throw NoSuchElementException("Stack is empty")
        }
        return array[size - 1]
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = ArrayIterator(size)

    @TestOnly
    internal fun capacity(): Int = array.size

    @TestOnly
    internal fun resize(capacity: Int) {
        val resizedArray = arrayOfNulls<Any>(capacity) as Array<Item>
        for (i in 0 until size) {
            resizedArray[i] = array[i]
        }
        array = resizedArray
    }

    private inner class ArrayIterator(
        private var index: Int
    ) : Iterator<Item> {
        override fun hasNext(): Boolean = index >= 0

        override fun next(): Item {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            return array[index--]
        }

    }
}