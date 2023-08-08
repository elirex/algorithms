package com.elirex.algorithms.strings

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TriesTest {

    @Test
    fun `trie SET`() {
        val tries = TrieSET()
        keys.forEach { key ->
            tries.add(key)
        }
        assertEquals(tries.toSet(), keys.toSet())
        assertEquals(7, tries.size)
        assertEquals("shells", tries.longestPrefixOf("shellsort"))
        assertEquals(null, tries.longestPrefixOf("xshellsort"))
        assertEquals(setOf("shore"), tries.keysWithPrefix("shor").toSet())
        assertEquals(setOf("she", "shells", "shore"), tries.keysWithPrefix("sh").toSet())
        assertEquals(emptySet(), tries.keysWithPrefix("shortening").toSet())
        assertEquals(setOf("shells"), tries.keysThatMatch(".he.l.").toSet())
    }

    @Test
    fun `trie ST`() {
        val tries = TrieST<Int>()
        keys.forEachIndexed { i, key ->
            tries.put(key, i)

        }
        assertEquals(tries.keys.toSet(), keys.toSet())
        assertEquals(7, tries.size)
        assertEquals("shells", tries.longestPrefixOf("shellsort"))
        assertEquals(null, tries.longestPrefixOf("xshellsort"))
        assertEquals(setOf("shore"), tries.keysWithPrefix("shor").toSet())
        assertEquals(setOf("she", "shells", "shore"), tries.keysWithPrefix("sh").toSet())
        assertEquals(emptySet(), tries.keysWithPrefix("shortening").toSet())
        assertEquals(setOf("shells"), tries.keysThatMatch(".he.l.").toSet())
    }

    companion object {
        private val keys = listOf(
            "she",
            "sells",
            "sea",
            "shells",
            "by",
            "the",
            "sea",
            "shore",
        )
    }
}