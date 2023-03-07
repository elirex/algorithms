package com.elirex.algorithms.searching

class SequentialSearchST<Key: Comparable<Key>, Value> : SymbolTable<Key, Value> {
    private var size: Int = 0
    private var first: Node<Key, Value>? = null


    private data class Node<Key, Value>(
        val key: Key,
        var value: Value? = null,
        var next: Node<Key,Value>? = null
    )


    override fun put(key: Key, value: Value?) {
        if (value == null) {
            remove(key)
            return
        }

        var curr: Node<Key, Value>? = first
        while (curr != null) {
            if (key == curr.key)  {
                curr.value = value
                return
            }
            curr = curr.next
        }
        first = Node(key, value, first)
        size++
    }

    override fun get(key: Key): Value? {
        var curr: Node<Key, Value>? = first
        while (curr != null) {
            if (key == curr.key)  {
                return curr.value
            }
            curr = curr.next
        }
        return null
    }

    override fun remove(key: Key) {
        first = remove(first, key)
    }

    private fun remove(node: Node<Key, Value>?, key: Key): Node<Key, Value>? {
        if (node == null) {
            return null
        }
        if (key == node.key) {
            size--
            return node.next
        }
        node.next = remove(node.next, key)
        return node
    }

    override fun contains(key: Key): Boolean {
        var curr: Node<Key, Value>? = first
        while (curr != null) {
            if (key == curr.key)  {
                return true
            }
            curr = curr.next
        }
        return false
    }

    override fun isEmpty(): Boolean = size() == 0

    override fun size(): Int = size

    override fun size(low: Key, high: Key): Int {
        TODO("Not yet implemented")
    }

    override fun min(): Key {
        TODO("Not yet implemented")
    }

    override fun max(): Key {
        TODO("Not yet implemented")
    }

    override fun select(k: Int): Key {
        TODO("Not yet implemented")
    }

    override fun removeMin() {
        TODO("Not yet implemented")
    }

    override fun removeMax() {
        TODO("Not yet implemented")
    }

    override fun keys(): Iterable<Key> {
        TODO("Not yet implemented")
    }

    override fun keys(low: Key, high: Key): Iterable<Key> {
        TODO("Not yet implemented")
    }

    override fun rank(key: Key): Int {
        TODO("Not yet implemented")
    }

    override fun ceiling(key: Key): Key {
        TODO("Not yet implemented")
    }

    override fun floor(key: Key): Key {
        TODO("Not yet implemented")
    }


}