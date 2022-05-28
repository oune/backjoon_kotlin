package test.line0528

import java.util.*

fun main() {
    println(solution(4, intArrayOf(2, 3)))
}

fun solution(n: Int, times: IntArray): Int {
    var ans = -1
    val visited = BooleanArray(n + 1) { false }
    val que = PriorityQueue { a:State, b:State ->
        a.time - b.time
    }
    que.offer(State(1, 0))
    visited[1] = true
    while (que.isNotEmpty()) {
        val now = que.poll()

        if (now.lines == n) {
            ans = now.time
            break
        }

        times.forEachIndexed {index, time ->
            if (now.lines >= index + 1) {
                val newLines = now.lines + index + 1
                if (newLines <= n) {
                    if (!visited[newLines]) {
                        que.offer(State(newLines, now.time + time))
                        visited[newLines] = true
                    }
                }
            }
        }
    }

    return ans
}

private data class State(val lines:Int, val time:Int)