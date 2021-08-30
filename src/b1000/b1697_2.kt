package b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    print(bfs(n, k))
}

private fun bfs(start: Int, end: Int): Int {
    val que = LinkedList<Int>()
    val visited = Array(100001) {0}

    que.offer(start)
    visited[start] = 0

    val moves = listOf<(Int) -> Int>({num -> num + 1}, {num -> num - 1}, {num -> num * 2})
    while(que.isNotEmpty()) {
        val t = que.poll()

        if (t == end) {
            return visited[t]
        }

        for (move in moves) {
            val moved = move(t)

            if (moved in 0..100000 && visited[moved] == 0) {
                que.offer(moved)
                visited[moved] = visited[t] + 1
            }
        }
    }

    return -1
}