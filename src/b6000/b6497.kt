package test.b6000

fun main() = with(System.`in`.bufferedReader()) {
    val out = System.out.bufferedWriter()

    while (true) {
        val (houses, roads) = readLine().split(' ').map { it.toInt() }
        if (houses == 0 && roads == 0)
            break

        data class Line(val from:Int, val to:Int, val distance: Int)
        val lines = Array(roads) {
            val (x, y, distance) = readLine().split(' ').map { it.toInt() }
            Line(x, y, distance)
        }.sortedBy { it.distance }

        val disjointSet = IntArray(houses) { it }
        fun findSet(vertex:Int):Int {
            if (vertex != disjointSet[vertex])
                disjointSet[vertex] = findSet(disjointSet[vertex])

            return disjointSet[vertex]
        }

        fun isUnion(a:Int, b:Int): Boolean{
            return findSet(a) == findSet(b)
        }

        fun unionSet(a:Int, b:Int) {
            disjointSet[findSet(a)] = findSet(b)
        }

        val total = lines.sumOf { it.distance }
        var sum = 0
        lines.forEach {
            val now = it

            if (!isUnion(now.from, now.to)) {
                unionSet(now.from, now.to)
                sum += now.distance
            }
        }
        out.appendLine("${total - sum}")
    }
    out.flush()
}