package com.elirex.algorithms.unionfind

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnionFindTest {

    companion object {
        private val tinyCase = Pair(10, listOf(
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
        ))
    }

    @Test
    fun `quick find`() {
        // given
        // when
        val uf: UnionFind = QuickFind(tinyCase.first)
        tinyCase.second.forEach { (p, q) ->
            if (uf.connected(p, q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }

    @Test
    fun `quick union`() {
        // given
        // when
        val uf: UnionFind = QuickUnion(tinyCase.first)
        tinyCase.second.forEach { (p, q) ->
            if (uf.connected(p, q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }

    @Test
    fun `weighted quick union`() {
        // given
        // when
        val uf: UnionFind = WeightedQuickUnion(tinyCase.first)
        tinyCase.second.forEach { (p, q) ->
            if (uf.connected(p, q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }

    @Test
    fun `path compression quick union`() {
        // given
        // when
        val uf: UnionFind = PathCompressionQuickUnion(tinyCase.first)
        tinyCase.second.forEach { (p, q) ->
            if (uf.connected(p, q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }


    @Test
    fun `weight by height quick union`() {
        // given
        // when
        val uf: UnionFind = WeightedByHeightQuickUnion(tinyCase.first)
        tinyCase.second.forEach { (p, q) ->
            if (uf.connected(p, q)) {
                return@forEach
            }
            uf.union(p, q)
        }

        // then
        assertEquals(2, uf.count)
    }

}