package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import java.lang.IllegalArgumentException

typealias Color = Boolean
class RedBlackBST<Key: Comparable<Key>, Value> : SymbolTable<Key, Value> {

    companion object {
        private const val RED: Color = true
        private const val BLOCK: Color = false
    }


    private inner class Node(
        var key: Key,
        var value: Value? = null,
        var left: Node? = null,
        var right: Node? = null,
        var color: Color = BLOCK,
        var size: Int = 0
    )

    private var root: Node? = null

    override fun put(key: Key, value: Value?) {
        TODO("Not yet implemented")

        root = put(root, key, value)?.apply { color = BLOCK }
    }

    private fun put(x: Node?, key: Key, value: Value?): Node? {
        if (x == null) {
            return Node(key, value, color = RED, size = 1)
        }

        val cmp = key.compareTo(x.key)
        when {
            cmp < 0 -> x.left = put(x.left, key, value)
            cmp > 0 -> x.right = put(x.right, key, value)
            else -> x.value = value
        }
        var h = x
        // rotates left any right-leaning 3 node
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h)
        }

        // rotates right the top link in a temporary 4-node with two left-leaning red links
        if (isRed(h?.left) && isRed(h?.left?.left)) {
            h = rotateRight(h)
        }

        // fips colors to pass a red link up the tree
        if (isRed(h?.left) && isRed(h?.right)) {
            flipColor(h)
        }
        h?.size = size(h?.left) + size(h?.right) + 1;
        return h
    }

    override fun get(key: Key): Value? {
        return get(root, key)
    }

    private fun get(x: Node?, key: Key): Value? {
        var n: Node? = x
        while (n != null) {
            val cmp = key.compareTo(n.key)
            n = when {
                cmp < 0 -> n.left
                cmp > 0 -> n.right
                else -> return n.value
            }
        }
        return null
    }

    override fun remove(key: Key) {
        if (!contains(key)) {
            return
        }

        if (!isRed(root?.left) && !isRed(root?.right)) {
            root?.color = RED
        }
        root = remove(root, key)
        if (!isEmpty()) {
            root?.color = BLOCK
        }
    }

    private fun remove(x: Node?, key: Key): Node? {
        var h: Node? = x
        if (key.compareTo(h!!.key) < 0) {
            if (!isRed(h?.left) && !isRed(h?.left?.left)) {
                h = moveRedRight(h)
            }
            h?.left = remove(h?.left, key)
        } else {
            if (isRed(h?.left)) {
                h = rotateRight(h)
            }
            if (key.compareTo(h!!.key) == 0 && h?.right == null) {
                return null
            }
            if (key.compareTo(h!!.key) == 0) {
                min(h?.right)?.also {
                     h.key = it.key
                     h.value = it.value
                }
                h?.right = removeMin(h?.right)
            } else {
                h?.right = remove(h?.right, key)
            }
        }
        return balance(h)
    }
    override fun contains(key: Key): Boolean {
        return get(key) != null
    }

    override fun isEmpty(): Boolean = root == null

    override fun size(): Int = size(root)

    override fun size(low: Key, high: Key): Int {
        TODO("Not yet implemented")
    }

    override fun min(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        return min(root)?.key!!
    }

    private fun min(x: Node?): Node? {
        if (x?.left == null) {
            return x
        } else {
            return min(x.left)
        }
    }

    override fun max(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        return max(root)?.key!!
    }

    private fun max(x: Node?): Node? {
        if (x?.right == null) {
            return x
        } else {
            return max(x.right)
        }
    }

    override fun select(k: Int): Key {
        if (k < 0 || k >= size()) {
            throw IllegalArgumentException("The $k rank is invalid")
        }
        return select(root, k)!!
    }

    private fun select(x: Node?, k: Int): Key? {
        if (x == null) {
            return null
        }
        val leftSize = size((x.left))
        if (leftSize > k) {
            return select(x.left, k)
        } else if (leftSize < k) {
            return select(x.right, k - leftSize - 1)
        } else {
            return x.key
        }
    }

    override fun removeMin() {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        if (!isRed(root?.left) && !isRed(root?.right)) {
            root?.color = RED
        }
        root = removeMin(root);
        if (!isEmpty()) {
            root?.color = BLOCK
        }
    }

    private fun removeMin(x: Node?): Node? {
        if (x?.left == null) {
            return null
        }
        var h: Node? = x
        if (!isRed(h?.left) && !isRed(h?.left?.left)) {
            h = moveRedLeft(h)
        }
        h?.left = removeMin(h?.left)
        return balance(h)
    }

    override fun removeMax() {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        if (!isRed(root?.left) && !isRed(root?.right)) {
            root?.color = RED
        }
        root = removeMax(root)
        if (!isEmpty()) {
            root?.color = BLOCK
        }
    }

    private fun removeMax(x: Node?): Node? {
        var h: Node? = x
        if (isRed(h?.left)) {
            h = rotateRight(h)
        }

        if (h?.right == null) {
            return null
        }

        if (!isRed(h?.right) && !isRed(h?.right?.left)) {
            h = moveRedRight(h)
        }
        h?.right = removeMax(h?.right)
        return balance(h)
    }

    override fun keys(): Iterable<Key> {
        if (isEmpty()) {
            return LinkedListQueue<Key>()
        }
        return keys(min(), max())
    }

    override fun keys(low: Key, high: Key): Iterable<Key> {
        val queue: Queue<Key> = LinkedListQueue<Key>()
        keys(root, queue, low, high)
        return queue
    }

    private fun keys(x: Node?, queue: Queue<Key>, low: Key, high: Key) {
        if (x == null) {
            return
        }
        val cmpLow = low.compareTo(x.key)
        val cmpHigh = high.compareTo(x.key)
        if (cmpLow < 0) {
            keys(x.left, queue, low, high)
        }
        if (cmpLow <= 0 && cmpHigh >= 0) {
            queue.enqueue(x.key)
        }
        if (cmpHigh > 0) {
            keys(x.right, queue, low, high)
        }
    }

    override fun rank(key: Key): Int {
        return rank(root, key)
    }

    private fun rank(x: Node?, key: Key): Int {
        if (x == null) {
            return 0
        }
        val cmp = key.compareTo(x.key)
        when {
            cmp < 0 -> return rank(x.left, key)
            cmp > 0 -> return 1 + rank(x.right, key) + size(x.left)
            else -> return size(x.left)
        }
    }

    override fun ceiling(key: Key): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        return ceiling(root, key)?.key ?: throw NoSuchElementException("the $key is too large")
    }

    private fun ceiling(x: Node?, key: Key): Node? {
        if (x == null) {
            return null
        }
        val cmp = key.compareTo(x.key)
        if (cmp == 0) {
            return x
        }
        if (cmp > 0) {
            return ceiling(x.right, key)
        }
        return ceiling(x.left, key) ?: x
    }

    override fun floor(key: Key): Key {
        if (isEmpty()) {
            throw NoSuchElementException("the tree is empty")
        }
        return floor(root, key)?.key ?: throw NoSuchElementException("the $key is too small")
    }

    private fun floor(x: Node?, key: Key): Node? {
        if (x == null) {
            return null
        }
        val cmp = key.compareTo(x.key)
        if (cmp == 0) {
            return x
        }
        if (cmp < 0) {
            return floor(x.left, key)
        }

        return floor(x.right, key) ?: x
    }

    private fun size(x: Node?): Int = x?.size ?: 0

    private fun isRed(x: Node?): Boolean = x?.color == RED

    private fun rotateLeft(h: Node?): Node? {
        assert(h == null && isRed(h?.right))
        return h?.right?.also { x: Node ->
            h.right = x.left
            x.left = h
            x.color = h.color
            h.color = RED
            x.size = h.size
            h.size = 1 + size(h.left) + size(h.right)
        }
    }


    private fun rotateRight(h: Node?): Node? {
        assert(h == null && isRed(h?.left))
        return h?.right?.also { x: Node ->
            h.left = x.right
            x.right = h
            x.color = h.color
            h.color = RED
            x.size = h.size
            h.size = 1 + size(h.left) + size(h.right)
        }
    }

    private fun flipColor(h: Node?) {
        h?.run {
            color = !color
            left?.run { color = !color }
            right?.run { color = !color }
        }
    }

    private fun moveRedLeft(x: Node?): Node? {
        flipColor(x)
        var h: Node? = x
        if (isRed(h?.right?.left)) {
            h?.right = rotateRight(h?.right)
            h = rotateLeft(h)
            flipColor(h)
        }
        return h
    }

    private fun moveRedRight(x: Node?): Node? {
        flipColor(x)
        var h: Node? = x
        if (isRed(h?.left?.left)) {
            h = rotateRight(h)
            flipColor(h)
        }
        return h
    }

    private fun balance(x: Node?): Node? {
        var h: Node? = x
        if (isRed(h?.right) && !isRed(h?.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h?.left) && isRed(h?.left?.left)) {
            h = rotateRight(h)
        }
        if (isRed(h?.left) && isRed(h?.right)) {
            flipColor(h)
        }
        h?.size = size(h?.left) + size(h?.right) + 1;
        return h
    }
}