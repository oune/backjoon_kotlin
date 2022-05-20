package b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().toCharArray()
    }
    val visited = Array(2) {
        Array(n) {
            BooleanArray(m) { false }
        }
    }

    val que = LinkedList<Point>()
    que.offer(Point(0, 0, 1, false))
    visited[0][0][0] = true

    var res = -1
    while (que.isNotEmpty()) {
        val now = que.poll()

        if (now.y == arr.lastIndex && now.x == arr[now.y].lastIndex ) {
            res = now.step
            break
        }

        listOf(
            {point:Point ->
                val (x, y, step, isBroken) = point
                Point(x + 1, y, step + 1, isBroken)
            },
            {point:Point ->
                val (x, y, step, isBroken) = point
                Point(x - 1, y, step + 1, isBroken)
            },
            {point:Point ->
                val (x, y, step, isBroken) = point
                Point(x, y + 1, step + 1, isBroken)
            },
            {point:Point ->
                val (x, y, step, isBroken) = point
                Point(x, y - 1, step + 1, isBroken)
            },
        ).forEach {
            val moved = it(now)
            val visitedIdx = if (moved.isBroken) 1 else 0

            if (moved.y in arr.indices && moved.x in arr[moved.y].indices) {// index check
                if (!visited[visitedIdx][moved.y][moved.x]) {// visit check
                    if (arr[moved.y][moved.x] == '0') {// wall check
                        que.offer(moved)
                        visited[visitedIdx][moved.y][moved.x] = true
                    } else if (!moved.isBroken) {
                        que.offer(moved.broke())
                        visited[1][moved.y][moved.x] = true
                    }
                }
            }
        }
    }

    println(res)
}

private data class Point (val x :Int, val y: Int, val step:Int, val isBroken: Boolean) {
    fun broke() :Point{
        return Point(this.x, this.y, this.step, true)
    }
}