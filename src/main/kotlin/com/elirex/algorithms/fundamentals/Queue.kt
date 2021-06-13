package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/12.
 */
interface Queue<Item> : Iterable<Item> {

    /**
     * Adds the item to this queue.
     * @param [item] the item to add to this queue.
     */
    fun enqueue(item: Item)

    /**
     * Removes and returns the last recently added item on this queue,
     * or null if this queue is empty.
     * @return the item that last recently added on this queue,
     *         or null if this queue is empty.
     */
    fun dequeue(): Item?

    /**
     * Returns true if this queue is empty; otherwise false.
     * @return true if this queue is empty;
     *         false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * Returns the number of items in this queue.
     * @return the [Int] type of the number of item in this queue.
     */
    fun size(): Int

    /**
     * Returns the item least recently added to this queue, or null this queue is empty.
     * @return the item least recently added to this queue,
     *         or null this queue is empty
     */
    fun peek(): Item?
}