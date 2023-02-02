import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    var size = readLine().toInt()
    val bw = System.out.bufferedWriter()
    var count = 1
    while(size != 0) {
        val map = Array(size) {
            readLine().split(" ").map { it.toInt() }
        }
        val ans = Array(size) {
            IntArray(size) { 150000 }
        }

        data class Point(val x:Int, val y:Int)
        val que = LinkedList<Point>()
        ans[0][0] = map[0][0]
        que.offer(Point(0, 0))

        while(que.isNotEmpty()) {
            val now = que.poll()

            listOf(
                {point:Point -> Point(point.x + 1, point.y)},
                {point:Point -> Point(point.x - 1, point.y)},
                {point:Point -> Point(point.x, point.y + 1)},
                {point:Point -> Point(point.x, point.y - 1)},
            ).forEach {move ->
                val moved = move(now)

                if (moved.y in ans.indices && moved.x in ans[moved.y].indices) {
                    val price = ans[now.y][now.x] + map[moved.y][moved.x]
                    if (ans[moved.y][moved.x] > price) {
                        ans[moved.y][moved.x] = price
                        que.offer(moved)
                    }
                }
            }
        }
        bw.appendLine("Problem $count: ${ans.last().last()}")
        size = readLine().toInt()
        count++
    }
    bw.flush()
    bw.close()
}