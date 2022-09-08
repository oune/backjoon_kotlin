package test.b1000

import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val (spaceGodCnt, lineCnt) = readLine().split(' ').map { it.toInt() }

    data class Point(val idx:Int, val x:Int, val y:Int)
    data class Line(val from:Point, val to:Point, val price:Double)

    val lines = mutableListOf<Line>()
    val points = mutableListOf<Point>()
    repeat(spaceGodCnt) { idx ->
        val (x, y) = readLine().split(' ').map { it.toInt() }
        val now = Point(idx, x, y)

        points.forEach { to ->
            val distance = sqrt((now.x - to.x).toDouble().pow(2) + (now.y - to.y).toDouble().pow(2))
            lines += Line(now, to, distance)
        }
        points += now
    }

    val disjointSet = IntArray(spaceGodCnt) { it }
    fun findSet(vertex: Int):Int {
        if (vertex != disjointSet[vertex])
            disjointSet[vertex] = findSet(disjointSet[vertex])

        return disjointSet[vertex]
    }
    fun isUnion(from:Int, to:Int):Boolean {
        return findSet(from) == findSet(to)
    }
    fun unionSet(from:Int, to:Int) {
        disjointSet[findSet(from)] = findSet(to)
    }

    repeat(lineCnt) {
        val (from, to) = readLine().split(' ').map { it.toInt() -1 }

        if (!isUnion(from, to)) {
            unionSet(from, to)
        }
    }

    var sum = 0.0
    lines.sortedBy { it.price }.forEach { (from, to, price) ->
        if (!isUnion(from.idx, to.idx)) {
            unionSet(from.idx, to.idx)
            sum += price
        }
    }
    println("%.2f".format(sum))
}