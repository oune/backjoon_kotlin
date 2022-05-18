package b9000

import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val peopleCnt = readLine().toInt()
    val listCnt = readLine().toInt()

    val graph = Array(peopleCnt + 1){ ArrayList<Int>() }
    repeat(listCnt) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    print(bfs(graph))
}

private fun bfs(graph: Array<ArrayList<Int>>): Int {
    val que = LinkedList<Pair<Int, Int>>()
    val visited = Array(graph.size){false}

    que.offer(Pair(1, 0))
    visited[1] = true

    var count = -1
    while(que.isNotEmpty()) {
        val t = que.poll()
        count++

        for (i in graph[t.first]) {
            if (!visited[i] && t.second < 2) {
                que.offer(Pair(i, t.second + 1))
                visited[i] = true
            }
        }
    }
    return count
}