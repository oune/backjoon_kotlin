import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().toCharArray()
    }

    println(bfs(arr))
}

private val moveList = listOf(
    { a: Int, b: Int -> Pair(a + 1, b) },
    { a: Int, b: Int -> Pair(a, b + 1) },
    { a: Int, b: Int -> Pair(a - 1, b) },
    { a: Int, b: Int -> Pair(a , b - 1) }
)

private fun bfs(arr: Array<CharArray>): Int {
    val que = LinkedList<Pair<Int, Int>>()
    val visited = Array(arr.size){
        Array(arr[0].size) {0}
    }

    que.offer(Pair(0, 0))
    visited[0][0] = 1

    while (que.isNotEmpty()) {
        val t = que.poll()

        for (move in moveList) {
            val moved = move(t.first, t.second)

            if (moved.first in arr.indices && moved.second in arr[moved.first].indices) {
                if (visited[moved.first][moved.second] == 0) {
                    if (arr[moved.first][moved.second] == '1') {
                        que.add(moved)
                        visited[moved.first][moved.second] = visited[t.first][t.second] + 1
                    }
                }
            }
        }
    }

    return visited[visited.size - 1][visited[0].size - 1]
}