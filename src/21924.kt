/*
MST
도로의 개수 : 500000
비용 : 1000000
>> 총 비용의 타입은 Long
일반적인 알고리즘 문제, 전체 비용을 저장하고 이후 그 차이와 비교
시간 복잡도 :  ElogE
공간 복잡도 :
12분 소요
*/
fun main() = with(System.`in`.bufferedReader()) {
    val (vertexCnt, edgeCnt) = readLine().split(" ").map { it.toInt() }

    data class Node(val from:Int, val to:Int, val cost:Int)

    var total = 0L
    val edges = List(edgeCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        total += cost
        Node(from, to, cost)
    }

    val set = DisjointSet(vertexCnt + 1)
    val sorted = edges.sortedBy { it.cost }

    var sum = 0L
    for ((from, to, cost) in sorted) {
        if (set.isUnion(from, to))
            continue

        set.unionSet(from, to)
        sum += cost
    }

    if (set.isAllConected()) {
        println(total - sum)
    } else {
        println(-1)
    }
}

class DisjointSet(size: Int) {
    private val parents = IntArray(size) { it }

    private fun findSet(idx:Int): Int {
        if (idx != parents[idx])
            parents[idx] = findSet(parents[idx])
        return parents[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa] = pb
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }

    fun isAllConected(): Boolean {
        val droped = parents.drop(1)
        val parent = findSet(droped.first())

        return droped.all { findSet(it) == parent }
    }
}