import com.sun.javafx.scene.control.skin.VirtualFlow
import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val nodeCnt = readLine().toInt()
    val graph = Array(nodeCnt + 1){ArrayList<Int>()}

    repeat(nodeCnt - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    val res = bfs(graph)
    for (i in (2 .. nodeCnt)) {
        println(res[i])
    }
}

private fun bfs(graph: Array<ArrayList<Int>>): Array<Int> {
    val que = LinkedList<Int>()
    val visited = Array(graph.size){0}

    que.offer(1)
    visited[1] = 1

    while(que.isNotEmpty()) {
        val t = que.poll()

        for (i in graph[t]) {
            if (visited[i] == 0) {
                que.offer(i)
                visited[i] = t
            }
        }
    }

    return visited
}