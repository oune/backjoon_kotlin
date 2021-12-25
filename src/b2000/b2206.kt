package b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().split("").filter{ it != ""}.map{ it.toInt() }.toIntArray()
    }
    val visited = Array(n) {
        BooleanArray(m) { false }
    }
    val moveList = listOf(
        {x:Int, y:Int -> Pair(x + 1, y)},
        {x:Int, y:Int -> Pair(x - 1, y)},
        {x:Int, y:Int -> Pair(x, y + 1)},
        {x:Int, y:Int -> Pair(x, y - 1)}
    )

    val que = LinkedList<Point>()
    que.offer(Point(0, 0, 1, false))
    visited[0][0] = true

    var res = -1
    while (que.isNotEmpty()) {
        val t = que.poll()

        if (t.x == m - 1 && t.y == n - 1) {
            res = t.step
            break
        }

        for (move in moveList) {
            val moved = move(t.x, t.y)

            if (moved.first in 0 until m && moved.second in 0 until n) {// index check
                if (!visited[moved.second][moved.first]) {// visit check
                    if (arr[moved.second][moved.first] == 0) {// wall check
                        que.offer(Point(moved.first, moved.second, t.step + 1, t.isBroken))
                    } else if (!t.isBroken) {
                        que.offer(Point(moved.first, moved.second, t.step + 1, true))
                    }
                    visited[moved.second][moved.first] = true
                }
            }
        }
    }

    println(res)
}

private class Point (val x :Int, val y: Int, val step:Int, val isBroken: Boolean)