package com.elirex.algorithms.strings

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class StringsSortTest {

    @Test
    fun `least-significant-digit (LSD)`() {
        val strings = arrayOf(
            "bed", "bug", "dad", "yes", "zoo",
            "now", "for", "tip", "ilk", "dim",
            "tag", "jot", "sob", "nob", "sky",
            "hut", "men", "egg", "few", "jay",
            "owl", "joy", "rap", "gig", "wee",
            "was", "wad", "fee", "tap", "tar",
            "dug", "jam", "all", "bad", "yet",
        )
        val w = 3

        val expected = arrayOf(
            "all", "bad", "bed", "bug", "dad",
            "dim", "dug", "egg", "fee", "few",
            "for", "gig", "hut", "ilk", "jam",
            "jay", "jot", "joy", "men", "nob",
            "now", "owl", "rap", "sky", "sob",
            "tag", "tap", "tar", "tip", "wad",
            "was", "wee", "yes", "yet", "zoo",
        )

        LSD.sort(strings, w)

        assertContentEquals(expected, strings)
    }

    @Test
    fun `most-significant-digit (MSD)`() {
        val strings = arrayOf(
            "she", "sells", "seashells", "by", "the", "sea", "shore",
            "the", "shells", "she", "sells", "are", "surely", "seashells",
        )

        val expected = arrayOf(
            "are",
            "by",
            "sea",
            "seashells",
            "seashells",
            "sells",
            "sells",
            "she",
            "she",
            "shells",
            "shore",
            "surely",
            "the",
            "the",
        )

        MSD.sort(strings)

        assertContentEquals(expected, strings)
    }

    @Test
    fun `three-way string quicksort`() {
        val strings = arrayOf(
            "she", "sells", "seashells", "by", "the", "sea", "shore",
            "the", "shells", "she", "sells", "are", "surely", "seashells",
        )

        val expected = arrayOf(
            "are",
            "by",
            "sea",
            "seashells",
            "seashells",
            "sells",
            "sells",
            "she",
            "she",
            "shells",
            "shore",
            "surely",
            "the",
            "the",
        )

        Quick3string.sort(strings)

        assertContentEquals(expected, strings)
    }
}