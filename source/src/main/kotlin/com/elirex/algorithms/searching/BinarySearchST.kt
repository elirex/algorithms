package com.elirex.algorithms.searching

import com.elirex.algorithms.queue.LinkedListQueue
import com.elirex.algorithms.queue.Queue

class BinarySearchST<Key: Comparable<Key>, Value>(
    capacity: Int = INIT_CAPACITY,
) : SymbolTable<Key, Value> {

    private var keys: Array<Key?>
    private var vals: Array<Value?>
    private var size: Int

    init {
        keys = arrayOfNulls<Comparable<*>>(capacity) as Array<Key?>
        vals = arrayOfNulls<Any?>(capacity) as Array<Value?>
        size = 0
    }


    override fun put(key: Key, value: Value?) {
        if (value == null) {
            remove(key)
            return
        }

        val index = rank(key)

        if (index < size && keys[index]?.compareTo(key) == 0) {
            vals[index] = value
            return
        }

        if (keys.size == size) {
            resize(2 * size)
        }
        
        var j = size
        while (j > index) {
            keys[j] = keys[j - 1]
            vals[j] = vals[j - 1]
            j--
        }

        keys[index] = key
        vals[index] = value
        size++
    }

    override fun get(key: Key): Value? {
        if (isEmpty()) {
            return  null
        }
        val index = rank(key)
        if (index < size && keys[index]?.compareTo(key) == 0) {
            return vals[index]
        }
        return null
    }

    override fun remove(key: Key) {
        if (isEmpty()) {
            return
        }

        val index = rank(key)

        if (index == size || keys[index]?.compareTo(key) != 0) {
            return
        }

        for (j in index until size - 1) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1]
        }
        size--
        keys[index] = null
        vals[index] = null
    }

    override fun contains(key: Key): Boolean = get(key) != null

    override fun isEmpty(): Boolean = size() == 0

    override fun size(): Int = size

    override fun size(low: Key, high: Key): Int {
        if (low > high) {
            return 0
        }
        if (contains(high)) {
            return rank(high) - rank(low) + 1
        } else {
            return rank(high) - rank(low)
        }
    }

    override fun min(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        return keys[0] ?: throw NoSuchElementException("Symbol table is underflow error")
    }

    override fun max(): Key {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        return keys.last() ?: throw NoSuchElementException("Symbol table is underflow error")
    }

    override fun select(k: Int): Key {
        if (k < 0 || k >= size()) {
            throw IllegalArgumentException("called select() with invalid argument $k")
        }
        return keys[k] ?: throw NoSuchElementException("Symbol table is underflow error with $k")
    }

    override fun removeMin() {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        remove(min())
    }

    override fun removeMax() {
        if (isEmpty()) {
            throw NoSuchElementException("Symbol table is empty")
        }
        remove(max())
    }

    override fun keys(): Iterable<Key> {
        return keys(min(), max())
    }

    override fun keys(low: Key, high: Key): Iterable<Key> {
        val queue: Queue<Key> = LinkedListQueue<Key>()
        if (low > high) {
            return queue
        }
        for (i in rank(low) until rank(high)) {
            queue.enqueue(keys[i]!!)
        }
        if (contains(high)) {
            queue.enqueue(keys[rank(high)]!!)
        }
        return queue
    }

    override fun rank(key: Key): Int {
        var low = 0
        var high = size - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            val cmp = key.compareTo(keys[mid]!!)
            if (cmp > 0) {
                low = mid + 1
            } else if (cmp < 0) {
                high = mid - 1
            } else {
                return mid;
            }
        }
        return low
    }

    override fun ceiling(key: Key): Key {
        val index = rank(key)
        if (index == size) {
            throw NoSuchElementException("argument to ceiling is too large")
        } else {
            return keys[index]!!
        }
    }

    override fun floor(key: Key): Key {
        val index = rank(key)
        if (index < size && keys[index]?.compareTo(key) == 0) {
            return keys[index]!!
        }
        if (index == 0) {
            throw NoSuchElementException("argument to floor() is too small")
        } else  {
            return keys[index - 1]!!
        }
    }

    private fun resize(capacity: Int) {
        val resizedKeys: Array<Key?> = arrayOfNulls<Comparable<*>>(capacity) as Array<Key?>
        val resizedVals: Array<Value?> = arrayOfNulls<Any>(capacity) as Array<Value?>

        for (i in 0 until size) {
            resizedKeys[i] = keys[i]
            resizedVals[i] = vals[i]
        }
        keys = resizedKeys
        vals = resizedVals
    }

    companion object {
        private const val INIT_CAPACITY: Int = 2
    }

}