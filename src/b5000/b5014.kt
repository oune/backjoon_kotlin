package test.b5000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (f, s, g, u, d) = readLine().split(" ").map { it.toInt() }

    var ans = -1
    val visited = IntArray(f + 1) { 0 }
    val que = LinkedList<Int>()
    que.offer(s)
    visited[s] = 1

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now == g) {
            ans = visited[now]
            break
        }

        val up = now + u
        if (up in 1..visited.lastIndex && visited[up] == 0) {
            que.offer(up)
            visited[up] = visited[now] + 1
        }

        val down = now - d
        if (down in 1..visited.lastIndex && visited[down] == 0) {
            que.offer(down)
            visited[down] = visited[now] + 1
        }
    }

    if (ans != -1) {
        print(ans - 1)
    } else {
        print("use the stairs")
    }
}