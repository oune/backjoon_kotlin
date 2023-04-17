/*
mst
mst 중에서 특성 p-q 를 연결하는 도로가 있는지 확인하기
kruskal = n log n
prim n log e

 */
fun main() {
    val testcaseCnt = readln().toInt()

    data class Edge(val from:Int, val to:Int, val cost:Int)
    val res = List(testcaseCnt) {
        val (n, m, p, q) = readln().split(" ").filter { it != "" }.map { it.toInt() }

        val edges = List(m) {
            val (from, to, cost) = readln().split(" ").filter { it != "" }.map { it.toInt() }
            Edge(from, to, cost)
        }

        val newEdges = mutableListOf<Pair<Int, Int>>()
        val set = DisjointSet(n + 1)

        val sorted = edges.sortedBy { it.cost }
        for ((from, to, _) in sorted) {
            if (set.isUnion(from, to))
                continue

            set.unionSet(from, to)
            newEdges.add(Pair(from, to))
        }

        val count = newEdges.count { it == Pair(p, q) || it == Pair(q, p) }
        val isAllConnected = set.isAllConnected()

        if (count > 0 && isAllConnected) "YES" else "NO"
    }

    println(res.joinToString("\n"))
}

class DisjointSet(size:Int) {
    private val parents = IntArray(size) { it }

    fun findSet(idx:Int): Int {
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

    fun isNotUnion(a:Int, b:Int) :Boolean {
        return !isUnion(a, b)
    }

    fun isAllConnected(): Boolean {
        for (i in 2 .. parents.lastIndex)
            if (isNotUnion(1, i))
                return false
        return true
    }
}