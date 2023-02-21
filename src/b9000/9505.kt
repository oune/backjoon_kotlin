import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val testcase = readLine().toInt()
    val sb = StringBuilder()

    data class Point(val x:Int, val y:Int)
    data class State (val point: Point, val cost:Int)

    val moveList = listOf(
        {point:Point -> Point(point.x + 1, point.y)},
        {point:Point -> Point(point.x - 1, point.y)},
        {point:Point -> Point(point.x, point.y + 1)},
        {point:Point -> Point(point.x, point.y - 1)},
    )

    repeat(testcase) {
        val (k, _, height) = readLine().split(" ").map { it.toInt() }

        val clingon = IntArray('Z' - 'A' + 1) { 0 }
        repeat(k) {
            val (name, time) = readLine().split(" ")
            clingon[name.first() - 'A'] = time.toInt()
        }

        val map = Array(height) {
            readLine().toList()
        }

        var start = Point(0, 0)
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == 'E')
                    start = Point(j, i)
            }
        }

        val ans = Array(map.size) {
            IntArray(map[it].size) {
                Int.MAX_VALUE
            }
        }
        ans[start.y][start.x] = 0

        val que = PriorityQueue<State>( compareBy { it.cost } )
        que.offer(State(start, 0))

        while (que.isNotEmpty()) {
            val now = que.poll()
            val x = now.point.x
            val y = now.point.y

            if (y == 0 || y == ans.lastIndex || x == 0 || x == ans[y].lastIndex) {
                sb.appendLine(ans[y][x])
                break
            }

            if (ans[y][x] < now.cost)
                continue

            for (move in moveList) {
                val moved = move(now.point)

                if (moved.y in ans.indices && moved.x in ans[moved.y].indices) {
                    val newCost = ans[y][x] + clingon[map[moved.y][moved.x] - 'A']

                    if (ans[moved.y][moved.x] > newCost) {
                        ans[moved.y][moved.x] = newCost
                        que.offer(State(moved, newCost))
                    }
                }
            }
        }
    }

    print(sb)
}
