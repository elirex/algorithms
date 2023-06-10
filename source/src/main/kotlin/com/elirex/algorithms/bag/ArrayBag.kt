package com.elirex.algorithms.bag

import org.jetbrains.annotations.TestOnly

@Suppress("UNCHECKED_CAST")
class ArrayBag<Item>(capacity: Int = DEFAULT_CAPACITY): Bag<Item> {

    private var array: Array<Item>
    private var size: Int

    init {
        array = (arrayOfNulls<Any>(capacity) as Array<Item>)
        size = 0
    }


    companion object {
        private const val DEFAULT_CAPACITY: Int = 8
    }

    override fun add(item: Item) {
        if (size == array.size) {
            resize(2 * array.size)
        }
        array[size++] = item
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = ArrayIterator()

    @TestOnly
    internal fun capacity(): Int = array.size

    @TestOnly
    internal fun resize(capacity: Int) {
        val newArray = (arrayOfNulls<Any>(capacity) as Array<Item>)
        array.forEachIndexed { i, item ->
            newArray[i] = item
        }
        array = newArray
    }


    private inner class ArrayIterator : Iterator<Item> {
        private var index = 0

        override fun hasNext(): Boolean = index < size

        override fun next(): Item {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            return array[index++]
        }
    }
}