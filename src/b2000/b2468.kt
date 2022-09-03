package test.b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().split(' ').map { it.toInt() }
    }

    var max = 0
    for (water in 0 .. 100) {
        data class State(val x:Int, val y:Int)

        var count = 0
        val visited = Array(size) { BooleanArray(size) { false } }
        val que = LinkedList<State>()

        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (!visited[i][j] && arr[i][j] > water) {
                    count++

                    que.offer(State(j, i))
                    visited[i][j] = true

                    while(que.isNotEmpty()) {
                        val now = que.poll()

                        listOf(
                            { from: State -> State(from.x + 1, from.y) },
                            { from: State -> State(from.x - 1, from.y) },
                            { from: State -> State(from.x, from.y + 1) },
                            { from: State -> State(from.x, from.y - 1) },
                        ).forEach {
                            val moved = it(now)

                            if (moved.y in arr.indices && moved.x in arr[moved.y].indices) {
                                if (!visited[moved.y][moved.x]) {
                                    if (arr[moved.y][moved.x] > water)
                                        que.offer(moved)
                                    visited[moved.y][moved.x] = true
                                }
                            }
                        }
                    }
                }
            }
        }

        max = max.coerceAtLeast(count)
    }
    println(max)
}