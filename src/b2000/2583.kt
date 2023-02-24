import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (height, width, k) = readLine().split(" ").map{ it.toInt() }

    val map = Array(height) {
        BooleanArray(width) { false }
    }

    repeat(k) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }

        for (i in y1 until y2) {
            for (j in x1 until x2) {
                map[i][j] = true
            }
        }
    }
    val visited = Array(map.size) {
        BooleanArray(map[it].size) { false }
    }
    val que = LinkedList<Pair<Int, Int>>()

    var ans = mutableListOf<Int>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (visited[i][j])
                continue

            val target = map[i][j]

            que.offer(Pair(j, i))
            visited[i][j] = true

            var size = 0
            while(que.isNotEmpty()) {
                val now = que.poll()
                size++

                val moves = listOf(
                    {point:Pair<Int, Int> -> Pair(point.first + 1, point.second)},
                    {point:Pair<Int, Int> -> Pair(point.first - 1, point.second)},
                    {point:Pair<Int, Int> -> Pair(point.first, point.second + 1)},
                    {point:Pair<Int, Int> -> Pair(point.first, point.second - 1)},
                )

                for (move in moves) {
                    val moved = move(now)

                    if (moved.second in map.indices && moved.first in map[moved.second].indices) {
                        if (map[moved.second][moved.first] != target)
                            continue
                        if (visited[moved.second][moved.first])
                            continue

                        que.offer(moved)
                        visited[moved.second][moved.first] = true
                    }
                }
            }

            if (!target)
                ans.add(size)
        }
    }
    println(ans.size)
    println(ans.sorted().joinToString(" "))
}