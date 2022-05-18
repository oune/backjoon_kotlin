import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }
    val tomatoes = List(m) {
        readLine().split(" ").filter { it != "" }.map{ it.toInt() }
    }

    val que = LinkedList<Pair<Int, Int>>()
    val visited = Array(m){IntArray(n) { -1 } }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (tomatoes[i][j] == 1) {
                que.add(Pair(j, i))
                visited[i][j] = 0
            } else if (tomatoes[i][j] == -1) {
                visited[i][j] = 0
            }
        }
    }

    val moveList = listOf(
        { x:Int, y:Int -> Pair(x + 1, y) },
        { x:Int, y:Int -> Pair(x - 1, y) },
        { x:Int, y:Int -> Pair(x, y + 1) },
        { x:Int, y:Int -> Pair(x, y - 1) }
    )

    while (que.isNotEmpty()) {
        val t = que.poll()

        for (move in moveList) {
            val moved = move(t.first, t.second)

            if (moved.first in 0 until n && moved.second in 0 until m) {
                if (tomatoes[moved.second][moved.first] != -1) {
                    if (visited[moved.second][moved.first] == -1) {
                        que.add(Pair(moved.first, moved.second))
                        visited[moved.second][moved.first] = visited[t.second][t.first] + 1
                    }
                }
            }
        }
    }

    println(
        if (visited.all { list -> list.all { count -> count != -1 } }) {
            visited.maxOf { it.maxOrNull()!! }
        } else { -1 }
    )
}