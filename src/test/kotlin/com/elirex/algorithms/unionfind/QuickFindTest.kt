package com.elirex.algorithms.unionfind

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class QuickFindTest {

    @Test
    fun `tiny union find`() {
        // given
        val n: Int = 10;
        val pairs = listOf(
            4 to 3,
            3 to 8,
            6 to 5,
            9 to 4,
            2 to 1,
            8 to 9,
            5 to 0,
            7 to 2,
            6 to 1,
            1 to 0,
            6 to 7,
        )

        // when
        val uf: UnionFind = QuickFind(n)
        pairs.forEach { (p, q) ->
            if (uf.find(p) == uf.find(q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }
}