package com.elirex.algorithms.strings

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class TrieSET() : Iterable<String> {

    private var root: Node? = null
    var size: Int = 0
        private set
    val isEmpty: Boolean
        get() = size == 0

    fun contains(key: String?): Boolean {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        val x: Node? = get(root, key, 0)
        return x?.isString ?: false
    }

    private fun get(
        x: Node?,
        key: String,
        d: Int,
    ): Node? {
        return when {
            x == null -> null
            d == key.length -> x
            else -> get(x.next[key[d].code], key, d + 1)
        }
    }

    fun add(key: String?) {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        root = add(root, key, 0)
    }

    private fun add(
        x: Node?,
        key: String,
        d: Int,
    ): Node {
        val node = x ?: Node()
        if (d == key.length) {
            if (!node.isString) {
                size++
            }
            node.isString = true
        } else {
            val c = key[d].code
            node.next[c] = add(node.next[c], key, d + 1)
        }
        return node
    }

    fun keysWithPrefix(prefix: String): Iterable<String> {
        val results: Queue<String> = LinkedListQueue()
        val x = get(root, prefix, 0)
        collect(x, StringBuilder(prefix), results)
        return results
    }

    private fun collect(
        x: Node?,
        prefix: StringBuilder,
        results: Queue<String>,
    ) {
        if (x == null) {
            return
        }

        if (x.isString) {
            results.enqueue(prefix.toString())
        }
        for (c in 0 until R) {
            prefix.append(c.toChar())
            collect(x.next[c], prefix, results)
            prefix.deleteCharAt(prefix.length - 1)
        }
    }


    override fun iterator(): Iterator<String> {
        return keysWithPrefix("").iterator()
    }

    fun keysThatMatch(pattern: String): Iterable<String> {
        val results: Queue<String> = LinkedListQueue()
        val prefix = StringBuilder()
        collect(root, prefix, pattern, results)
        return results
    }

    private fun collect(
        x: Node?,
        prefix: StringBuilder,
        pattern: String,
        results: Queue<String>,
    ) {
        if (x == null) {
            return
        }
        val d = prefix.length
        if (d == pattern.length && x.isString) {
            results.enqueue(prefix.toString())
        }
        if (d == pattern.length) {
            return
        }
        val c: Char = pattern[d]
        if (c == '.') {
            for (ch in 0 until R) {
                prefix.append(ch.toChar())
                collect(x.next[ch], prefix, pattern, results)
                prefix.deleteCharAt(prefix.length - 1)
            }

        } else {
            prefix.append(c)
            collect(x.next[c.code], prefix, pattern, results)
            prefix.deleteCharAt(prefix.length - 1)
        }
    }

    fun longestPrefixOf(query: String?): String? {
        if (query == null) {
            throw IllegalArgumentException("query must be not null")
        }
        val length = longestPrefixOf(root, query, 0, -1)
        if (length == -1) {
            return null
        }
        return query.substring(0, length)
    }

    private fun longestPrefixOf(
       x: Node?,
       query: String,
       d: Int,
       length: Int,
    ): Int {
        if (x == null) {
            return length
        }
        val len = when (x.isString) {
            true -> d
            else -> length
        }
        if (d == query.length) {
            return len
        }
        val c = query[d]
        return longestPrefixOf(x.next[c.code], query, d + 1, len)
    }

    fun delete(key: String?) {
        if (key == null) {
            throw IllegalArgumentException("key must be not null")
        }
        root = delete(root, key, 0)
    }

    private fun delete(
        x: Node?,
        key: String,
        d: Int,
    ): Node? {
        if (x == null) {
            return null
        }
        if (d == key.length) {
            if (x.isString) {
                size--
            }
            x.isString = false
        } else {
            val c = key[d].code
            x.next[c] = delete(x.next[c], key, d + 1)
        }
        if (x.isString) {
            return x
        }
        for (c in 0 until R) {
            if (x.next[c] != null)  {
                return x
            }
        }
        return null
    }

    data class Node(
        val next: Array<Node?> = arrayOfNulls(R),
        var isString: Boolean = false
    )

    companion object {
        private const val R = 256 // extended ASCII
    }

}