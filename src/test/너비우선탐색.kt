package test.test

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (nodeCnt, lineCnt) = readLine().split(" ").map { it.toInt() }

    val lines = Array(nodeCnt + 1) {
        mutableListOf<Int>()
    }
    repeat(lineCnt) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        lines[start].add(end)
        lines[end].add(start)
    }

    fun bfs(start:Int) {
        val visited = BooleanArray(lines.size) { false }
        val que = LinkedList<Int>()

        que.offer(start)
        visited[start] = true

        while(que.isNotEmpty()) {
            val now = que.poll()
            print("$now ")

            lines[now].forEach {
                if (!visited[it]) {
                    que.offer(it)
                    visited[it] = true
                }
            }
        }
        println()
    }

    fun dfs(visited:BooleanArray, now:Int) {
        println("$now ")
        visited[now] = true

        lines[now].forEach { next ->
            if (!visited[next]) {
                dfs(visited, next)
                visited[next]
            }
        }
    }

    for (i in 1 .. lines.lastIndex) {
        bfs(i)
    }

    for (i in 1 .. lines.lastIndex) {
        val visited = BooleanArray(lines.size) { false }
        dfs(visited, i)
        println()
    }
}