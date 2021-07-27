import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (n , m) = readLine().split(" ").map {
        it.toInt()
    }

    val visited = Array(n + 1) { false }
    val map = Array(n + 1){ ArrayList<Int>() }

    repeat(m) {
        val (u, v) = readLine().split(" ").map {
            it.toInt()
        }
        map[v].add(u)
        map[u].add(v)
    }

    var count = 0
    for (i in 1 .. n) {
        if (!visited[i]) {
            dfs(map, visited, i)
            count++
        }
    }

    print(count)
}

fun bfs(map: Array<ArrayList<Int>>, visited: Array<Boolean>, start: Int) {
    val que : Queue<Int> = LinkedList<Int>()
    que.add(start)

    while(que.isNotEmpty()) {
        val now = que.poll()
        val route = map[now]

        route.forEach {
            if (!visited[it]) {
                que.add(it)
                visited[it] = true
            }
        }
    }
}

fun dfs(map: Array<ArrayList<Int>>, visited: Array<Boolean>, start: Int) {
    val now = map[start]
    visited[start] = true

    now.filter {
        !visited[it]
    }.forEach{
        dfs(map, visited, it)
    }
}