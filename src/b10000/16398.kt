fun main() = with(System.`in`.bufferedReader()) {
    val startCnt = readLine().toInt()
    data class Edge(val from:Int, val to:Int, val cost:Int)

    val edges = mutableListOf<Edge>()

    repeat(startCnt) { i ->
        val stars = readLine().split(" ").map { it.toInt() }
        for (j in stars.indices) {
            if (i == j)
                continue

            edges.add(Edge(i, j, stars[j]))
        }
    }


    val set = DisjointSet(startCnt)
    val sorted = edges.sortedBy { it.cost }
    var sum = 0L
    for ((from, to, cost) in sorted) {
        if (set.isUnion(from, to))
            continue

        set.unionSet(from, to)
        sum += cost
    }

    println(sum)
}

class DisjointSet(size:Int) {
    private val parents = IntArray(size) { it }

    private fun findSet(idx:Int): Int {
        if (idx != parents[idx])
            parents[idx] = findSet(parents[idx])

        return parents[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val parentA = findSet(a)
        val parentB = findSet(b)
        parents[parentA] = parentB
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }
}