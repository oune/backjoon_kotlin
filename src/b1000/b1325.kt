package b1000

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map{ it.toInt() }

    val graph = Array(n + 1) { ArrayList<Int>() }
    repeat(m) {
        val (a, b) = readLine().split(" ").map{ it.toInt() }
        graph[b].add(a)
    }

    var max = 0
    val res = ArrayList<Int>()
    for (i in 1..n) {
        val now = bfs(graph, i)

        if (max == now)
            res.add(i)
        if ( max < now ) {
            max = now
            res.clear()
            res.add(i)
        }
    }

    val out = BufferedWriter(OutputStreamWriter(System.out))
    for (i in res) {
        out.append("$i ")
    }
    out.flush()
    out.close()
}

private fun bfs (graph: Array<ArrayList<Int>>, start: Int): Int {
    val que = LinkedList<Int>()
    val visited = Array(graph.size){ false }
    var count = 0

    que.offer(start)
    visited[start] = true

    while(que.isNotEmpty()) {
        val t = que.poll()

        for (i in graph[t]) {
            if (!visited[i]) {
                que.offer(i)
                visited[i] = true
                count++

            }
        }
    }

    return count
}