import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val starCnt = readLine().toInt()

    data class Point(val idx:Int, val x:Double, val y:Double)
    data class Distance(val from:Point, val to:Point, val price:Double)

    val stars = mutableListOf<Point>()
    val distances = mutableListOf<Distance>()
    repeat(starCnt) { idx ->
        val (x, y) = readLine().split(" ").map { it.toDouble() }
        val now = Point(idx, x, y)

        stars.forEach { star ->
            val distance = sqrt((star.x - x).pow(2) + (star.y - y).pow(2))
            distances += Distance(now, star, distance)
        }
        stars += now
    }

    val disjointSet = IntArray(starCnt) { it }
    fun findSet(vertex:Int): Int {
        if (vertex != disjointSet[vertex])
            disjointSet[vertex] = findSet(disjointSet[vertex])

        return disjointSet[vertex]
    }

    fun isUnion(a:Int, b:Int) :Boolean {
        return findSet(a) == findSet(b)
    }

    fun unionSet(a:Int, b:Int) {
        disjointSet[findSet(a)] = findSet(b)
    }

    var sum = 0.0
    distances.sortedBy { it.price }.forEach { (from, to, distance) ->
        if (!isUnion(from.idx, to.idx)) {
            unionSet(from.idx, to.idx)
            sum += distance
        }
    }
    println(String.format("%.2f", sum))
}