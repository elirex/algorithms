package com.elirex.algorithms.strings

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue
import kotlin.math.round

class TST<Value>() {

    private var root: Node<Value>? = null
    var size: Int = 0
        private set

    fun contains(key: String): Boolean {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        return get(key) != null
    }

    fun get(key: String?): Value? {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        if (key.isEmpty()) {
            throw IllegalArgumentException("the length of key must be not empty")
        }
        return get(root, key, 0)?.value
    }

    private fun get(
        x: Node<Value>?,
        key: String,
        d: Int,
    ): Node<Value>? {
        if (x == null) {
            return null
        }
        if (key.isEmpty()) {
            throw IllegalArgumentException("the length of key must be not empty")
        }
        val c = key[d]
        return when {
            c < x.c -> get(x.left, key, d)
            c > x.c -> get(x.right, key, d)
            d < key.length - 1 -> get(x.mid, key, d + 1)
            else -> x
        }
    }

    fun put(key: String?, value: Value?) {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        if (!contains(key)) {
            size++
        } else if (value == null) {
            size--
        }
        root = put(root, key, value, 0)
    }

    private fun put(
        x: Node<Value>?,
        key: String,
        value: Value?,
        d: Int,
    ): Node<Value> {
        val c = key[d]
        val node = x ?: Node(c)
        when {
            c < node.c -> node.left = put(node.left, key, value, d)
            c > node.c -> node.right = put(node.right, key, value, d)
            d < key.length - 1 -> node.mid = put(node.mid, key, value, d + 1)
            else -> node.value = value
        }
        return node
    }

    fun longestPrefixOf(query: String): String? {
        if (query == null) {
            throw IllegalArgumentException("query must be not null")
        }
        if (query.isEmpty()) {
            return null
        }
        var length = 0
        var x = root
        var i = 0
        while (x != null && i < query.length) {
            val c = query[i]
            x = when {
                c < x.c -> x.left
                c > x.c -> x.right
                else -> {
                    i++
                    if (x.value != null) {
                        length = i
                    }
                    x.mid
                }
            }
        }
        return query.substring(0, length).takeIf { it.isNotEmpty() }
    }

    fun keys(): Iterable<String> {
        val queue: Queue<String> = LinkedListQueue()
        collect(root, StringBuilder(), queue)
        return queue
    }

    fun keysWithPrefix(prefix: String?): Iterable<String> {
        if (prefix == null) {
            throw IllegalArgumentException("prefix must be not null")
        }
        val queue: Queue<String> = LinkedListQueue()
        val x = get(root, prefix, 0)
        if (x == null) {
            return queue
        }
        if (x.value != null) {
            queue.enqueue(prefix)
        }
        collect(x.mid, StringBuilder(prefix), queue)
        return queue
    }

    private fun collect(
        x: Node<Value>?,
        prefix: StringBuilder,
        queue: Queue<String>,
    ) {
        if (x == null) {
            return
        }
        collect(x.left, prefix, queue)
        if (x.value != null) {
            queue.enqueue(prefix.toString() + x.c)
        }
        collect(x.mid, prefix.append(x.c), queue)
        prefix.deleteCharAt(prefix.lastIndex)
        collect(x.right, prefix, queue)
    }

    fun keysThatMatch(pattern: String): Iterable<String> {
        val queue: Queue<String> = LinkedListQueue()
        collect(root, StringBuilder(), pattern, 0, queue)
        return queue
    }

    private fun collect(
        x: Node<Value>?,
        prefix: StringBuilder,
        pattern: String,
        i: Int,
        queue: Queue<String>,
    ) {
        if (x == null) {
            return
        }
        val c = pattern[i]
        when {
        }
        if (c == '.' || c < x.c) {
            collect(x.left, prefix, pattern, i, queue)
        }
        if (c == '.' || c == x.c) {
            if (i == pattern.length - 1 && x.value != null) {
                queue.enqueue(prefix.toString() + x.c)
            }
            if (i < pattern.length - 1) {
                collect(x.mid, prefix.append(x.c), pattern, i + 1, queue)
                prefix.deleteCharAt(prefix.lastIndex)
            }
        }
        if (c == '.' || c > x.c) {
            collect(x.right, prefix, pattern, i, queue)
        }
    }

    private data class Node<Value>(
        val c: Char,
        var left: Node<Value>? = null,
        var mid: Node<Value>? = null,
        var right: Node<Value>? = null,
        var value: Value? = null,
    )
}