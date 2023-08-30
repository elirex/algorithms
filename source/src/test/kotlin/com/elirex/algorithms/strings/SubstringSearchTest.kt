package com.elirex.algorithms.strings

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class SubstringSearchTest {

    @TestFactory
    fun `KMP`(): List<DynamicTest> {
        data class Case(
            val pattern: String,
            val txt: String,
            val expected: Int,
        )
        val testcases = listOf(
            Case(
                pattern = "abracadabra",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 14,
            ),
            Case(
                pattern = "rab",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 8,
            ),
            Case(
                pattern = "bcara",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 34,
            ),
            Case(
                pattern = "rabrabracad",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 23,
            ),
            Case(
                pattern = "abacad",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 0,
            ),
        )
        return testcases.mapIndexed { index, (pattern, txt, expected) ->
            DynamicTest.dynamicTest("case $index should be $expected") {
                val kmp = KMP(pattern)
                assertEquals(expected, kmp.search(txt))
            }
        }
    }

    @TestFactory
    fun `BoyerMoore`(): List<DynamicTest> {
        data class Case(
            val pattern: String,
            val txt: String,
            val expected: Int,
        )
        val testcases = listOf(
            Case(
                pattern = "abracadabra",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 14,
            ),
            Case(
                pattern = "rab",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 8,
            ),
            Case(
                pattern = "bcara",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 34,
            ),
            Case(
                pattern = "rabrabracad",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 23,
            ),
            Case(
                pattern = "abacad",
                txt = "abacadabrabracabracadabrabrabracad",
                expected = 0,
            ),
        )
        return testcases.mapIndexed { index, (pattern, txt, expected) ->
            DynamicTest.dynamicTest("case $index should be $expected") {
                val bm = BoyerMoore(pattern)
                assertEquals(expected, bm.search(txt))
            }
        }
    }
}