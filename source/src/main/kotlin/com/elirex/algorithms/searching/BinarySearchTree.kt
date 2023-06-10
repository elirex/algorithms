package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class BinarySearchTree<Key: Comparable<Key>, Value> : SymbolTable<Key, Value> {

    private var root: Node? = null

    private inner class Node(
        val key: Key,
        var value: Value?,
        var size: Int = 0
    ) {
        var left: Node? = null
        var right: Node? = null
    }

    override fun put(key: Key, value: Value?) {
        if (value == null) {
            remove(key)
            return
        }
        root = put(root, key, value)
    }

    private fun put(node: Node?, key: Key, value: Value): Node {
        if (node == null) {
            return Node(key, value, 1)
        }
        val cmp = key.compareTo(node.key)
        when {
            cmp < 0 -> node.left = put(node.left, key, value)
            cmp > 0 -> node.right = put(node.right, key, value)
            else -> node.value = value
        }
        node.size = 1 + size(node.left) + size(node.right)
        return node
    }

    override fun get(key: Key): Value? = get(root, key)

    private fun get(node: Node?, key: Key): Value? {
        if (node == null) {
            return null
        }
        val cmp = key.compareTo(node.key)
        return when {
            cmp < 0 -> get(node.left, key)
            cmp > 0 -> get(node.right, key)
            else -> node.value
        }
    }

    override fun remove(key: Key) {
        root = remove(root, key)
    }

    private fun remove(node: Node?, key: Key): Node? {
        if (node == null) {
            return null
        }
        var x = node
        val cmp = key.compareTo(x.key)
        when {
            cmp < 0 -> x.left = remove(x.left, key)
            cmp > 0 -> x.right = remove(x.right, key)
            else -> {
                if (x.right== null) {
                    return x.left
                }
                if (x.left == null) {
                    return x.right
                }
                val t = x
                x = min(t.right)?.apply {
                    right = removeMin(t.right)
                    left = t.left
                }
            }
        }
        return x?.apply {
            size = size(left) + size(right) +1
        }
    }

    override fun contains(key: Key): Boolean = get(key) != null

    override fun isEmpty(): Boolean = size() == 0

    override fun size(): Int = size(root)

    override fun size(low: Key, high: Key): Int {
        if (low > high) {
            return 0
        }
        return if (contains(high)) {
            rank(high) - rank(low) + 1
        } else {
            rank(high) - rank(low)
        }
    }

    private fun size(node: Node?): Int = node?.size ?: 0

    override fun min(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the symbol table is empty")
        }
        return min(root)!!.key
    }

    private fun min(node: Node?): Node? {
        if (node?.left == null) {
            return node
        } else {
            return min(node.left)
        }
    }

    override fun max(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the symbol table is empty")
        }
        return max(root)!!.key
    }

    private fun max(node: Node?): Node? {
        if (node?.right == null) {
            return node
        } else {
            return max(node.right)
        }
    }

    override fun select(k: Int): Key {
        if (k < 0 || k >= size()) {
            throw IllegalArgumentException("the argument $k is invalid")
        }
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        return select(root, k)!!
    }

    private fun select(node: Node?, k: Int): Key? {
        if (node == null) {
            return null
        }
        val leftSize = size(node.left)
        return when {
            leftSize > k -> select(node.left, k)
            leftSize < k -> select(node.right, k - leftSize - 1)
            else -> node.key
        }
    }

    override fun removeMin() {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        root = removeMin(root)
    }

    private fun removeMin(node: Node?): Node? {
        if (node == null) {
            return null
        }
        if (node.left == null) {
            return node.right
        }
        node.left = removeMin(node.left)
        node.size = 1 + size(node.left) + size(node.right)
        return node
    }

    override fun removeMax() {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        root = removeMax(root)
    }

    private fun removeMax(node: Node?): Node? {
        if (node == null) {
            return null
        }
        if (node.right == null) {
            return node.left
        }
        node.right = removeMax(node.right)
        node.size = 1 + size(node.left) + size(node.right)
        return node
    }

    override fun keys(): Iterable<Key> {
        if (isEmpty()) {
            return LinkedListQueue<Key>()
        } else {
            return keys(min(), max())
        }
    }

    override fun keys(low: Key, high: Key): Iterable<Key> {
        val queue = LinkedListQueue<Key>()
        keys(root, queue, low, high)
        return queue
    }

    private fun keys(node: Node?, queue: Queue<Key>, low: Key, high: Key) {
        if (node == null) {
            return
        }
        val cmpLow = low.compareTo(node.key)
        val cmpHigh = high.compareTo(node.key)
        if (cmpLow < 0) {
            keys(node.left, queue, low, high)
        }
        if (cmpLow <= 0 && cmpHigh >= 0)  {
            queue.enqueue(node.key)
        }
        if (cmpHigh > 0) {
            keys(node.right, queue, low, high)
        }
    }

    override fun rank(key: Key): Int = rank(root, key)

    private fun rank(node: Node?, key: Key): Int {
        if (node == null) {
            return 0
        }
        val cmp = key.compareTo(node.key)
        return when {
            cmp < 0 -> rank(node.left, key)
            cmp > 0 -> 1 + size(node.left) + rank(node.right, key)
            else -> size(node.left)
        }
    }

    override fun ceiling(key: Key): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        val target = ceiling(root, key)
        return target?.key ?: throw NoSuchElementException("argument $key is too large")
    }

    private fun ceiling(node: Node?, key: Key): Node? {
        if (node == null) {
            return null
        }
        val cmp = key.compareTo(node.key)
        return when {
            cmp == 0 -> node
            cmp < 0 -> ceiling(node.left, key) ?: node
            else -> ceiling(node.right, key)
        }
    }

    override fun floor(key: Key): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        val target = floor(root, key)
        return target?.key ?: throw NoSuchElementException("argument $key is too small")
    }

    private fun floor(node: Node?, key: Key): Node? {
        if (node == null) {
            return null
        }
        val cmp = key.compareTo(node.key)
        return when {
            cmp == 0 -> node
            cmp < 0 -> floor(node.left, key)
            else -> floor(node.right, key) ?: node
        }
    }

    fun levelOrder(): Iterable<Key> {
        val keys = LinkedListQueue<Key>()
        val queue = LinkedListQueue<Node?>()
        queue.enqueue(root)
        while (!queue.isEmpty()) {
            val x = queue.dequeue() ?: continue
            keys.enqueue(x.key)
            queue.enqueue(x.left)
            queue.enqueue(x.right)
        }
        return keys
    }

}