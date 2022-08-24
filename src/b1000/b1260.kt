package test.b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, v) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) {
        PriorityQueue<Int>()
    }

    repeat(m) {
        val (from, to) = readLine().split(" ").map{ it.toInt() }
        graph[from].add(to)
        graph[to].add(from)
    }

    val out = System.out.bufferedWriter()
    fun bfs(start:Int) {
        val que = LinkedList<Int>()
        val visited = BooleanArray(graph.size) { false }

        que.offer(start)
        visited[start] = true

        while(que.isNotEmpty()) {
            val now = que.poll()

            out.append("$now ")

            graph[now].forEach { moved ->
                if (!visited[moved]) {
                    que.offer(moved)
                    visited[moved] = true
                }
            }
        }
        out.newLine()
    }

    fun dfs(visited:BooleanArray, start:Int) {
        out.append("$start ")
        visited[start] = true

        graph[start].forEach { moved ->
            if (!visited[moved]) {
                dfs(visited, moved)
            }
        }
    }

    bfs(v)

    val visited = BooleanArray(graph.size) { false }
    dfs(visited, v)
    out.newLine()
    out.flush()
}