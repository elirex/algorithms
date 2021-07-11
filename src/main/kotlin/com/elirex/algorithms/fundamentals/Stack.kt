package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/12.
 */
interface Stack<Item> : Iterable<Item> {
  /**
   * Adds the item to this stack.
   * @param [item] the item to add
   */
  fun push(item: Item)

  /**
   * Removes and returns the item most recently added to this stack,
   * or null if this stack is empty.
   * @return item that most recently added to this stack,
   *         or null if this stack is empty
   */
  fun pop(): Item?

  /**
   * Returns the item most most recently added to this stack,
   * or null if this stack is empty.
   * @return item that most recently added to this stack,
   *         or null if this stack is empty
   */
  fun peek(): Item?

  /**
   * Is this stack empty?
   * @return true if this stack is empty;
   *         false otherwise
   */
  fun isEmpty(): Boolean

  /**
   * Returns the number of items in this stack.
   * @return the number of items in this tack
   */
  fun size(): Int
}