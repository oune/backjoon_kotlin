package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val stones = readLine().split(" ").filter{ it != "" }.map { it.toInt() }
    val start = readLine().toInt() - 1

    val visited = BooleanArray(stones.size) { false }
    val que = LinkedList<Int>()

    que.offer(start)
    visited[start] = true

    while(que.isNotEmpty()) {
        val now = que.poll()

        listOf(
            now - stones[now], now + stones[now]
        ).forEach { moved ->
            if (moved in visited.indices) {
                if(!visited[moved]) {
                    que.offer(moved)
                    visited[moved] = true
                }
            }
        }
    }

    val res = visited.count { it }
    println(res)
}