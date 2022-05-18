package test.b5000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (f, s, g, u, d) = readLine().split(" ").map { it.toInt() }

    var ans = -1
    val visited = BooleanArray(f + 1) { false }
    val que = LinkedList<State>()
    que.offer(State(s, 0))

    while(que.isNotEmpty()) {
        val now = que.poll()
        visited[now.floor] = true

        if (now.floor == g) {
            ans = now.count
            break
        }

        val up = now.floor + u
        if (up in 1..visited.lastIndex && !visited[up]) {
            que.offer(State(up, now.count + 1))
        }

        val down = now.floor - d
        if (down in 1..visited.lastIndex && !visited[down]) {
            que.offer(State(down, now.count + 1))
        }
    }

    if (ans != -1) {
        print(ans)
    } else {
        print("use the stairs")
    }
}

private data class State(val floor:Int, val count:Int)