package com.elirex.algorithms.strings

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class NFATest {

    @TestFactory
    fun `NFA test`(): List<DynamicTest> {
        data class Cases(
            val regexp: String,
            val text: String,
            val expected: Boolean
        )
        val cases = listOf(
            Cases(
                regexp = "((A*B|AC)D)",
                text = "AAAABD",
                expected = true,
            ),
            Cases(
                regexp = "(A*B|AC)D",
                text = "AAAAC",
                expected = false,
            ),
            Cases(
                regexp = "(a|(bc)*d)*",
                text = "abcbcd",
                expected = true,
            ),
            Cases(
                regexp = "(a|(bc)*d)*",
                text = "abcbcbcdaaaabcbcdaaaddd",
                expected = true,
            ),
        )
        return cases.mapIndexed { index, (regpex, text, expected) ->
            DynamicTest.dynamicTest("case $index should be $expected") {
                val nfa = NFA(regpex)
                assertEquals(expected, nfa.recognizes(text))
            }
        }
    }
}