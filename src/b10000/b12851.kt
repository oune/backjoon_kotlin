package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, sister) = readLine().split(" ").map { it.toInt() }
    val size = 100000 + 1
    val visited = BooleanArray(size) { false }

    var min = Int.MAX_VALUE
    var count = 0

    val que = LinkedList<State>()
    que.offer(State(n, 0))
    visited[n] = true

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.subin == sister) {
            if (now.time < min) {
                min = now.time
                count = 1
            } else if (now.time == min) {
                count++
            }
            continue
        }

        listOf(
            {state:State ->
                val (subin, time) = state
                State(subin + 1, time + 1)
            },
            {state:State ->
                val (subin, time) = state
                State(subin - 1, time + 1)
            },
            {state:State ->
                val (subin, time) = state
                State(subin * 2, time + 1)
            },
        ).forEach {
            val moved = it(now)

            if (moved.subin in visited.indices) {
                if (!visited[moved.subin]) {
                    que.offer(moved)
                    visited[moved.subin] = moved.subin != sister
                }
            }
        }
    }


    println(min)
    println(count)
}

private data class State(val subin:Int, val time:Int)