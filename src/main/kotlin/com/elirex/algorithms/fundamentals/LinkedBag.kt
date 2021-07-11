package com.elirex.algorithms.fundamentals

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/6/13.
 */
class LinkedBag<Item> : Bag<Item> {

  private var first: Node? = null
  private var size: Int = 0


  override fun add(item: Item) {
    first = Node(item, first)
    size++
  }

  override fun isEmpty(): Boolean = first == null

  override fun size(): Int = size

  override fun iterator(): Iterator<Item> = LinkedIterator()

  private inner class LinkedIterator : Iterator<Item> {
    private var current: Node? = first

    override fun hasNext(): Boolean = current != null

    override fun next(): Item {
      if (hasNext().not()) throw NoSuchElementException()
      val item = current?.item ?: throw NoSuchElementException()
      current = current?.next
      return item
    }
  }

  private inner class Node(val item: Item, var next: Node? = null)

}

fun main(args: Array<String>) {
  val bag: Bag<String> = LinkedBag()
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