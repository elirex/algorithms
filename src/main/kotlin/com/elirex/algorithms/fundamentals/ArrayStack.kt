package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/13.
 */
class ArrayStack<Item> : Stack<Item> {
    private var items: Array<Item?> = arrayOfNulls<Any>(2) as Array<Item?>
    private var size: Int = 0

    override fun push(item: Item) {
        if (size == items.size) resize(items.size * 2)
        items[size++] = item
    }

    override fun pop(): Item? {
        if (isEmpty()) return null
        val item: Item? = items[size - 1]
        items[size - 1] = null
        size--
        if (size > 0 && size == items.size / 4)  resize(items.size / 2)
        return item
    }

    override fun peek(): Item? {
        if (isEmpty()) return null
        return items[size - 1]
    }

    override fun isEmpty(): Boolean = size == 0

    override fun size(): Int = size

    override fun iterator(): Iterator<Item> = ReverseArray()

    private fun resize(capacity: Int) {
        assert(capacity >= size)
        val temp: Array<Item?> = arrayOfNulls<Any>(capacity) as Array<Item?>
        for (i in 0 until size) {
            temp[i] = items[i]
        }
        items = temp
    }

    private inner class ReverseArray : Iterator<Item> {
        private var i: Int = size - 1
        override fun hasNext(): Boolean = i >= 0

        override fun next(): Item {
            if (hasNext().not()) throw NoSuchElementException()
            return items[i--] ?: throw NoSuchElementException()
        }
    }

}

fun main(args: Array<String>) {
    val stack: Stack<String> = ArrayStack<String>()
    stack.push("A")
    stack.push("B")
    stack.push("C")
    stack.push("D")
    stack.push("E")
    stack.push("F")

    for (element in stack) {
        println(element)
    }

    println("pop: ${stack.pop()}")
    println("peek: ${stack.peek()}")
}