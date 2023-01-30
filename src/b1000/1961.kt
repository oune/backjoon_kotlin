package b1000
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (x, y) = readLine().split(" ").map { it.toInt() }
    val map = Array(y) {
        readLine().split("").filter { it != "" }.map { it.toInt() }
    }

    data class Point(val x:Int, val y:Int, val price:Int)

    val que = PriorityQueue<Point>(compareBy { it.price })
    val ans = Array(y) {
        IntArray(x) { Int.MAX_VALUE }
    }

    que.offer(Point(0, 0, 0))
    ans[0][0] = 0


    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.price > ans[now.y][now.x])
            continue

        listOf(
            { a:Point -> Point(a.x + 1, a.y, 0) },
            { a:Point -> Point(a.x - 1, a.y, 0) },
            { a:Point -> Point(a.x, a.y + 1, 0) },
            { a:Point -> Point(a.x, a.y - 1, 0) },
        ).forEach {
            val moved = it(now)

            if (moved.y in ans.indices) {
                if (moved.x in ans[moved.y].indices) {
                    if (ans[moved.y][moved.x] > ans[now.y][now.x] + map[moved.y][moved.x]) {
                        ans[moved.y][moved.x] = ans[now.y][now.x] + map[moved.y][moved.x]
                        que.offer(moved)
                    }
                }
            }
        }
    }

    println(ans.last().last())
}