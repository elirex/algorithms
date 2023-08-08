package com.elirex.algorithms.strings

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class TrieST<Value> {

    private var root: Node<Value>? = null
    var size: Int = 0
        private set
    val isEmpty: Boolean
        get() = size == 0
    val keys: Iterable<String>
        get() = keysWithPrefix("")

    fun contains(key: String?): Boolean {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        return get(key) != null
    }

    fun get(key: String?): Value? {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
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
        if (d == key.length) {
            return x
        }
        val c = key[d].code
        return get(x.next[c], key, d + 1)
    }

    fun put(key: String?, value: Value?) {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        if (value == null) {
            delete(key)
        } else {
            root = put(root, key, value, 0)
        }
    }

    private fun put(
        x: Node<Value>?,
        key: String,
        value: Value,
        d: Int,
    ): Node<Value> {
        val node = x ?: Node()
        if (d == key.length) {
            if (node.value == null) {
                size++
            }
            node.value = value
            return node
        }
        val c = key[d].code
        node.next[c] = put(node.next[c], key, value, d + 1)
        return node
    }

    fun keysWithPrefix(prefix: String): Iterable<String> {
        val results: Queue<String> = LinkedListQueue()
        val x = get(root, prefix, 0)
        collect(x, StringBuilder(prefix), results)
        return results
    }

    private fun collect(
        x: Node<Value>?,
        prefix: StringBuilder,
        results: Queue<String>,
    ) {
        if (x == null) {
            return
        }
        if (x.value != null) {
            results.enqueue(prefix.toString())
        }
        for (c in 0 until R) {
            prefix.append(c.toChar())
            collect(x.next[c], prefix, results)
            prefix.deleteCharAt(prefix.lastIndex)
        }
    }

    fun keysThatMatch(pattern: String): Iterable<String> {
        val results: Queue<String> = LinkedListQueue()
        collect(root, StringBuilder(), pattern, results)
        return results
    }

    private fun collect(
        x: Node<Value>?,
        prefix: StringBuilder,
        pattern: String,
        results: Queue<String>,
    ) {
        if (x == null) {
            return
        }
        val d = prefix.length
        if (d == pattern.length && x.value != null) {
            results.enqueue(prefix.toString())
        }
        if (d == pattern.length) {
            return
        }
        val c = pattern[d]
        if (c == '.') {
            for (ch in 0 until R) {
                prefix.append(ch.toChar())
                collect(x.next[ch], prefix, pattern, results)
                prefix.deleteCharAt(prefix.lastIndex)
            }
        } else {
            prefix.append(c)
            collect(x.next[c.code], prefix, pattern, results)
            prefix.deleteCharAt(prefix.lastIndex)
        }
    }

    fun longestPrefixOf(query: String?): String? {
        if (query == null) {
            throw IllegalArgumentException("query must be not null")
        }
        val length = longestPrefixOf(root, query, 0, -1)
        if (length == -1) {
            return null
        } else {
            return query.substring(0, length)
        }
    }

    private fun longestPrefixOf(
        x: Node<Value>?,
        query: String,
        d: Int,
        length: Int,
    ): Int {
        if (x == null) {
            return length
        }
        val len = when(x.value) {
            null -> length
            else -> d
        }
        if (d == query.length) {
            return len
        }
        val c = query[d].code
        return longestPrefixOf(x.next[c], query, d + 1, len)
    }

    fun delete(key: String?) {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        root = delete(root, key, 0)
    }

    private fun delete(
        x: Node<Value>?,
        key: String,
        d: Int,
    ): Node<Value>? {
        if (x == null) {
            return null
        }
        if (d == key.length) {
            if (x.value != null) {
                size--
            }
            x.value = null
        } else {
            val c = key[d].code
            x.next[c] = delete(x.next[c], key, d + 1)
        }

        if (x.value != null) {
            return x
        }
        for (c in 0 until R) {
            if (x.next[c] != null)  {
                return x
            }
        }
        return null
    }

    private data class Node<Value>(
        val next: Array<Node<Value>?> = arrayOfNulls(R),
        var value: Value? = null
    )

    companion object {
        private const val R = 256 // extended ASCII
    }
}