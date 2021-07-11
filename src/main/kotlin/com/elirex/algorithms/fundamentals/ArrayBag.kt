package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/8.
 */
class ArrayBag<Item> : Bag<Item> {

  private var items: Array<Item> = arrayOfNulls<Any>(2) as Array<Item>
  private var size: Int = 0

  override fun add(item: Item) {
    if (size == items.size) resize(items.size * 2)
    items[size++] = item
  }

  override fun isEmpty(): Boolean = size == 0

  override fun size(): Int = size

  override fun iterator(): Iterator<Item> = ArrayIterator()

  private fun resize(capacity: Int) {
    assert(capacity >= size)
    val temp: Array<Item> = arrayOfNulls<Any>(capacity) as Array<Item>
    for (i in 0 until size) {
      temp[i] = items[i]
    }
    items = temp
  }

  private inner class ArrayIterator : Iterator<Item> {
    private var i: Int = 0

    override fun hasNext(): Boolean = i < size

    override fun next(): Item {
      if (hasNext().not()) throw NoSuchElementException()
      else return items[i++]
    }
  }
}

fun main(args: Array<String>) {
  val bag: Bag<String> = ArrayBag()
  bag.add("A")
  bag.add("B")
  bag.add("C")
  bag.add("D")
  bag.add("E")
  bag.add("F")

  for (element: String in bag) {
    println(element)
  }

}