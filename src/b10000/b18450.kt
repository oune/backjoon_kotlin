package b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(' ').map { it.toInt() }
    val arr = Array(n) {
        readLine().split(' ').map { it.toInt() }
    }
    var (s, qY, qX) = readLine().split(' ').map { it.toInt() }

    data class Pos (val x:Int, val y:Int, val depth: Int)
    val que = LinkedList<Pos>()

    val visited = Array(arr.size) {
        BooleanArray(arr[it].size) { false }
    }

    qX--
    qY--

    que.offer(Pos(qX, qY, 0))
    visited[qY][qX] = true

    var min = 2000
    while (que.isNotEmpty()) {
        val now = que.poll()

        if (now.depth > s)
            continue

        val virus = arr[now.y][now.x]
        if (virus != 0 && virus < min) {
            min = virus
            s = now.depth
        }

        listOf(
            {x: Int, y: Int, d:Int -> Pos(x + 1, y, d + 1)},
            {x: Int, y: Int, d:Int -> Pos(x - 1, y, d + 1)},
            {x: Int, y: Int, d:Int -> Pos(x, y + 1, d + 1)},
            {x: Int, y: Int, d:Int -> Pos(x, y - 1, d + 1)},
        ).forEach {
            val moved = it(now.x, now.y, now.depth)

            if (moved.depth <= s) {
                if (moved.y in arr.indices && moved.x in arr[moved.y].indices) {
                    if (!visited[moved.y][moved.x]) {
                        que.offer(moved)
                        visited[moved.y][moved.x] = true
                    }
                }
            }
        }
    }

    println(if (min == 2000) '0' else min)
}