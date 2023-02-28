import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (houseCnt, bridgeCnt) = readLine().split(" ").filter { it != "" }.map { it.toInt() }
    val (start, end) = readLine().split(" ").map { it.toInt() }

    data class Node(val from:Int, val to :Int, val limit:Int)
    val bridges = List(bridgeCnt) {
        val (from, to, limit) = readLine().split(" ").filter { it != "" }.map { it.toInt() }
        Node(from, to, limit)
    }

    val houses = IntArray(houseCnt + 1) { it }
    fun findSet(idx:Int): Int {
        if (idx != houses[idx])
            houses[idx] = findSet(houses[idx])

        return houses[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)

        houses[pa] = pb
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }

    var ans = 0
    val sorted = bridges.sortedBy { -it.limit }
    for ((from, to, limit) in sorted) {
        if (!isUnion(from, to)) {
            unionSet(from, to)
        }

        if (isUnion(start, end)) {
            ans = limit
            break
        }
    }

    println(ans)
}