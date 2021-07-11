package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/8.
 */
interface Bag<Item> : Iterable<Item> {
  /**
   * Adds the item to the bag.
   * @param [item] the item to add to the bag.
   */
  fun add(item: Item)

  /**
   * Returns true if the bag is empty; otherwise false.
   * @return true if the bag is empty;
   *         false otherwise
   */
  fun isEmpty(): Boolean

  /**
   * Returns the number of items in the bag.
   * @return the [Int] type of the number of item in the bag.
   */
  fun size(): Int
}