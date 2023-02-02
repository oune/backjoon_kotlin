import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val map = Array(size) {
        readLine().map { it.digitToInt() }.toIntArray()
    }

    val ans = Array(size) {
        IntArray(size) { 1000 }
    }
    ans[0][0] = 0
    data class Point(val x:Int, val y:Int)

    val que = LinkedList<Point>()
    que.offer(Point(0, 0))

    while (que.isNotEmpty()) {
        val now = que.poll()

        listOf(
            {point:Point -> Point(point.x + 1, point.y)},
            {point:Point -> Point(point.x - 1, point.y)},
            {point:Point -> Point(point.x, point.y + 1)},
            {point:Point -> Point(point.x, point.y - 1)},
        ).forEach { move ->
            val moved = move(now)

            if (moved.y in ans.indices && moved.x in ans[moved.y].indices) {
                val price = ans[now.y][now.x] + if (map[moved.y][moved.x] == 1) 0 else 1
                if (ans[moved.y][moved.x] > price) {
                    ans[moved.y][moved.x] = price
                    que.offer(moved)
                }
            }
        }
    }

    println(ans.last().last())
}