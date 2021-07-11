package com.elirex.algorithms.unionfind

/**
 * Created by Wang, Sheng-Yuan (Elirex) on 2021/7/11.
 */
open class UnionFind(private var size: Int) {

  private val parent: IntArray
  private val rank: ByteArray

  init {
    if (size < 0) throw IllegalArgumentException()
    parent = IntArray(size)
    rank = ByteArray(size)
    for (i in 0 until size) {
      parent[i] = i
      rank[i] = 0
    }
  }

  /**
   * Merges the set containing element [p] with the set containing element [q].
   */
  fun union(p: Int, q: Int) {
    val rootP = find(p)
    val rootQ = find(q)
    if (rootP == rootQ) return
    when {
      rank[rootP] < rank[rootQ] -> parent[rootP] = rootQ
      rank[rootP] > rank[rootQ] -> parent[rootQ] = rootP
      else -> {
        parent[rootQ] = rootP
        rank[rootP]++
      }
    }
    size--
  }

  /**
   * Returns the canonical element of the set containing element [p].
   * @param [p] an element.
   * @return the canonical element of the set containing element [p].
   */
  fun find(p: Int): Int {
    validate(p)
    var pp: Int = p
    while (pp != parent[pp]) {
      parent[pp] = parent[parent[pp]]
      pp = parent[pp]
    }
    return pp
  }

  /**
   * Returns true if the two elements are in the same set.
   * @param p one element.
   * @param q the other element.
   * @return true if [p] and [q] are in the same set; else otherwise.
   */
  fun connected(p: Int, q: Int): Boolean = find(p) == find(q)

  /**
   * Returns the number of sets.
   * @return the number of sets that 1 ~ N.
   */
  fun count(): Int = size
  
  private fun validate(p: Int) {
    val n = parent.size
    if (p < 0 || p >= n) throw IllegalArgumentException("index $p is not between 0 and ${n - 1}")
  }
}

fun main() {
  val uf: UnionFind = UnionFind(10)
  uf.union(4, 3)
  uf.union(3, 8)
  uf.union(6, 5)
  uf.union(9, 4)
  uf.union(2, 1)
  uf.union(5, 0)
  uf.union(7, 2)
  uf.union(6, 1)
  println("${uf.count()} components")
}