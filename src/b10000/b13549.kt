package b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(' ').map { it.toInt() }

    data class State (val pos: Int, val time:Int)

    val que = LinkedList<State>()
    val visited = BooleanArray(100001) { false }

    que.offer(State(n, 0))
    visited[n] = true

    var ans = Int.MAX_VALUE

    while (que.isNotEmpty()) {
        val now = que.poll()

        if (now.pos == k)
            ans = ans.coerceAtMost(now.time)

        listOf(
            { x: State -> State(x.pos * 2, x.time) },
            { x: State -> State(x.pos + 1, x.time + 1) },
            { x: State -> State(x.pos - 1, x.time + 1) },
        ).forEach {
            val moved = it(now)

            if (moved.pos in visited.indices) {
                if (!visited[moved.pos]) {
                    que.offer(moved)
                    visited[moved.pos] = true
                }
            }
        }
    }

    print(ans)
}