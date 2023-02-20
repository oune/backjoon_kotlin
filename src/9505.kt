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

    fun findStart(map: Array<List<Int>>): Point {
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == -1) {
                    return Point(j, i)
                }
            }
        }
        return Point(-1, -1)
    }

    repeat(testcase) {
        val (k, _, height) = readLine().split(" ").map { it.toInt() }

        val clingon = IntArray('Z' - 'A' + 1) { 0 }
        repeat(k) {
            val (name, time) = readLine().split(" ")
            clingon[name.first() - 'A'] = time.toInt()
        }
        clingon['E' - 'A'] = -1

        val map = Array(height) {
            readLine().map { char -> clingon[char - 'A'] }
        }

        val ans = Array(map.size) {
            IntArray(map[it].size) {
                Int.MAX_VALUE
            }
        }

        val start = findStart(map)
        ans[start.y][start.x] = 0
        clingon['E' - 'A'] = 0

        val que = PriorityQueue<State>( compareBy { it.cost })
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
                    val newCost = ans[y][x] + map[moved.y][moved.x]

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
