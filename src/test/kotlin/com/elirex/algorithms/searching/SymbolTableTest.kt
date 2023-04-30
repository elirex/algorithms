package com.elirex.algorithms.searching

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SymbolTableTest {

    @Test
    fun `ordered symbol table`() {
        val st = OrderedST<String, Int>()
        input.forEach { (k, v) ->
            st.put(k, v)
        }

        generateTestCases().forEach {
            when (it) {
                is TestCase.Rank -> assertEquals(it.expected, st.rank(it.key))
                is TestCase.Select -> assertEquals(it.expected, st.select(it.k))
                is TestCase.RangedKeys -> assertEquals(it.expected, it.range.run { st.keys(first, second).toList() })
                is TestCase.RangedSize -> assertEquals(it.expected, it.range.run { st.size(first, second) })
            }
        }
    }

    @Test
    fun `binary search symbol table`() {
        val st = BinarySearchST<String, Int>()
        input.forEach { (k, v) ->
            st.put(k, v)
        }

        generateTestCases().forEach {
            when (it) {
                is TestCase.Rank -> assertEquals(it.expected, st.rank(it.key))
                is TestCase.Select -> assertEquals(it.expected, st.select(it.k))
                is TestCase.RangedKeys -> assertEquals(it.expected, it.range.run { st.keys(first, second).toList() })
                is TestCase.RangedSize -> assertEquals(it.expected, it.range.run { st.size(first, second) })
            }
        }
    }

    @Test
    fun `binary search tree symbol table`() {
        val st = BinarySearchTree<String, Int>()
        input.forEach { (k, v) ->
            st.put(k, v)
        }
        generateTestCases().forEach {
            when (it) {
                is TestCase.Rank -> assertEquals(it.expected, st.rank(it.key))
                is TestCase.Select -> assertEquals(it.expected, st.select(it.k))
                is TestCase.RangedKeys -> assertEquals(it.expected, it.range.run { st.keys(first, second).toList() })
                is TestCase.RangedSize -> assertEquals(it.expected, it.range.run { st.size(first, second) })
            }
        }
    }

    @Test
    fun `separate chaining hash symbol table`() {
        val symbolTable = SeparateChainingHashST<String, Int>()
        input.forEach { (key, value) ->
            symbolTable.put(key, value)
        }
        assertTrue(symbolTable.contains("S"))
        assertEquals(0,  symbolTable.get("S"))
        assertEquals(12,  symbolTable.get("E"))
        assertEquals(input.size, symbolTable.size)
        symbolTable.delete("S")
        assertFalse(symbolTable.contains("S"))
    }

    @Test
    fun `linear probing hash symbol table`() {
        val symbolTable = LinearProbingHashST<String, Int>()
        input.forEach { (key, value) ->
            symbolTable.put(key, value)
        }
        assertTrue(symbolTable.contains("S"))
        assertEquals(0,  symbolTable.get("S"))
        assertEquals(12,  symbolTable.get("E"))
        assertEquals(input.size, symbolTable.size)
        symbolTable.delete("S")
        assertFalse(symbolTable.contains("S"))
    }

    private fun generateTestCases() = listOf(
        TestCase.Rank("E", 2),
        TestCase.Select(4, "L"),
        TestCase.RangedKeys(Pair("D", "O"), listOf("E", "H", "L", "M")),
        TestCase.RangedSize(Pair("D", "O"), 4)
    )

    private sealed interface TestCase {
        data class Rank(
            val key: String,
            val expected: Int
        ) : TestCase
        data class Select(
            val k: Int,
            val expected: String
        ) : TestCase
        data class RangedKeys(
            val range: Pair<String, String>,
            val expected: List<String>
        ) : TestCase
        data class RangedSize(
            val range: Pair<String, String>,
            val expected: Int
        ) : TestCase
    }

    companion object {
        val input = mapOf(
            "S" to 0,
            "E" to 1,
            "A" to 2,
            "R" to 3,
            "C" to 4,
            "H" to 5,
            "E" to 6,
            "X" to 7,
            "A" to 8,
            "M" to 9,
            "P" to 10,
            "L" to 11,
            "E" to 12,
        )
    }
}