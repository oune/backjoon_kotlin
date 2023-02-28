fun main() = with(System.`in`.bufferedReader()) {
    val (nodeCnt, edgeCnt, turnCnt) = readLine().split(" ").map { it.toInt() }
    data class Edge(val from:Int, val to:Int, val cost:Int)
    val edges = List(edgeCnt) { cost ->
        val (from, to) = readLine().split(" ").map { it.toInt() - 1 }
        Edge(from, to, cost + 1)
    }

    val ans = mutableListOf<Int>()
    var sorted = edges.sortedBy { it.cost }
    repeat(turnCnt) {
        val set = DisjointSet(nodeCnt)

        var sum = 0
        for ((from, to, cost) in sorted) {
            if (set.isUnion(from, to))
                continue

            set.unionSet(from, to)
            sum += cost
        }

        ans.add(if (set.isMST()) sum else 0)
        sorted = sorted.drop(1)
    }

    println(ans.joinToString(" "))
}

class DisjointSet(size:Int) {
    private val parent = IntArray(size) { it }
    private fun findSet(idx:Int): Int {
        if (idx != parent[idx])
            parent[idx] = findSet(parent[idx])
        return parent[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parent[pa] = pb
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }

    fun isMST(): Boolean {
        val first = parent.first()
        return parent.all { isUnion(first, it) }
    }
}