import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val computerCount = readLine().toInt()
    val graph = Array(computerCount + 1) {ArrayList<Int> ()}
    val lineCount = readLine().toInt()

    repeat(lineCount) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    print(bfs(graph))
}

private fun bfs(graph:Array<ArrayList<Int>>): Int {
    val que = LinkedList<Int>()
    val visited = Array(graph.size){false}

    que.offer(1)
    visited[1] = true

    var count = 0
    while (que.isNotEmpty()) {
        val t = que.poll()

        for (point in graph[t]) {
            if (!visited[point]) {
                count++
                que.offer(point)
                visited[point] = true
            }
        }
    }

    return count
}