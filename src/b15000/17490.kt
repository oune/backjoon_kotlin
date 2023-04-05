import java.util.LinkedList
import kotlin.system.exitProcess

/*
최소 신장트리
중간의 섬을 거쳐서 모든 강의 동을 연결하는 최소의 비용을 계산
공사중인 도로를 제거하는 부분이 핵심

시간초과
공사중인 도로는 제거하는데 사용한 removed 의 시간복잡도가 n 이 발생함.

런타임 에러 (NumberFormat)
k 값의 범위가 long

1시간 14분
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (_n, _m, _limit) = readLine().split(" ").filter { it != "" }
    val n = _n.toInt()
    val m = _m.toInt()
    val limit = _limit.toLong()
    val costs = readLine().split(" ").filter { it != "" }.map { it.toInt() }.toIntArray()
    val disconnects = BooleanArray(n.toInt() + 1) { false }

    repeat(m.toInt()) {
        val (from, to) = readLine().split(" ").filter { it != "" }.map { it.toInt() }.sorted()

        if (to - from > 1)
            disconnects[to] = true
        else
            disconnects[from] = true
    }



    data class Edge(val from:Int, val to:Int, val cost:Int)
    val edges = LinkedList<Edge>()
    for (from in 1 .. n) {
        val to = if (from != n) from + 1 else 1

        if (disconnects[from])
            continue

        edges.add(Edge(from, to, 0))
    }

    val parents = IntArray(n + 1) { it }

    fun findSet(idx:Int): Int {
        if (parents[idx] != idx)
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

    for ((from, to, _) in edges) {
        if (isUnion(from, to))
            continue

        unionSet(from, to)
    }
    edges.clear()

    for (to in costs.indices) {
        val from = 0
        edges.add(Edge(from, to + 1, costs[to]))
    }

    if (m < 2) {
        println("YES")
        exitProcess(0)
    }

    var sum = 0L
    for ((from, to, cost) in edges.sortedBy { it.cost }) {
        if (isUnion(from, to))
            continue

        unionSet(from, to)
        sum += cost
    }

    println(if (sum <= limit) "YES" else "NO")
}