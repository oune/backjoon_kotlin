package b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(101) { 0 }
    val visited = BooleanArray(101) { false }

    repeat(n + m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        arr[a] = b
    }

    val que = LinkedList<Pair<Int, Int>>()
    val moveList = listOf(1, 2, 3, 4, 5, 6)

    que.offer(Pair(1, 0))

    while(que.isNotEmpty()) {
        val (now, count) = que.poll()
        visited[now] = true

        if (now == 100) {
            print(count)
            break
        }

        moveList.filter { now + it in 0 .. 100 }.forEach {
            val moved = if (arr[now + it] > 0) {
                arr[now + it]
            } else if (arr[now + it] == 0) {
                now + it
            } else {
                -1
            }

            if (moved != -1)
                if (!visited[moved])
                    que.offer(Pair(moved, count + 1))
        }
    }
}