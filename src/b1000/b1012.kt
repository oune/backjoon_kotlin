package b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val test = readLine().toInt()
    repeat(test) {
        val (m, n, k) = readLine().split(" ").map { it.toInt() }
        val arr = Array(n){Array(m) {0} }

        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            arr[y][x] = 1
        }

        var count = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[i][j] == 1) {
                    count++
                    bfs(arr, j, i)
                }
            }
        }

        println(count)
    }
}

fun bfs(graph: Array<Array<Int>>, x: Int, y: Int) {
    val que = LinkedList<Pair<Int, Int>>()
    que.offer(Pair(x, y))
    graph[y][x] = 0

    val visit = listOf<Pair<Int,Int>>(Pair(0 , -1), Pair(0 , 1), Pair(-1 , 0), Pair(1 , 0))

    while(que.isNotEmpty()) {
        val t = que.poll()

        for (pair in visit) {
            val a = t.first + pair.first
            val b = t.second + pair.second

            if (a in 0 until graph[0].size && b in graph.indices ) {
                if(graph[b][a] == 1) {
                    que.offer(Pair(a, b))
                    graph[b][a] = 0
                }
            }
        }
    }
}