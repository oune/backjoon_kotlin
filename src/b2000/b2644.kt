import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val peopleCount = readLine().toInt() + 1
    val (a, b) = readLine().split(" ").map { it.toInt() }
    val m = readLine().toInt()

    val graph = Array(peopleCount){ ArrayList<Int>() }
    repeat(m) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        graph[x].add(y)
        graph[y].add(x)
    }

    print(bfs(graph, a, b))
}

private fun bfs(graph:Array<ArrayList<Int>>, start:Int, end:Int): Int {
    val que = LinkedList<Pair<Int, Int>>()
    val visited = Array(graph.size){false}

    que.offer(Pair(start, 0))
    visited[start] = true

    while (que.isNotEmpty()) {
        val t = que.poll()

        if (t.first == end) {
            return t.second
        }

        for (i in graph[t.first]) {
            if (!visited[i]){
                que.offer(Pair(i, t.second + 1))
                visited[i] = true
            }
        }
    }

    return -1
}