import java.util.*

fun main() {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val visited = Array(n + 1) { false }
    val map = Array<List<Int>>(n + 1){ emptyList()}

    for (i in 1 .. m) {
        st = StringTokenizer(readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()

        map[u] = map[u] + listOf(v)
        map[v] = map[v] + listOf(u)
    }

    var count = 0
    for (i in 1 .. n) {
        if (!visited[i]) {
            bfs(map, visited, i)
            count++
        }
    }

    print(count)
}

fun bfs(map: Array<List<Int>>, visited: Array<Boolean>, start: Int) {
    var que = listOf(start)

    while(que.isNotEmpty()) {
        val now = que.first();
        val route = map[now];

        route.forEach {
            if (!visited[it]) {
                que = que + it
                visited[it] = true;
            }
        }

        que = que.drop(1)
    }
}