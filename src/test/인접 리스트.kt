package test

fun main() {
    val (points, lines) = readLine()!!.split(" ").map { it.toInt() }

    val list = Array(points) { ArrayList<Int>() }

    repeat(lines) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        list[a] += b
        list[b] += a
    }
    list.forEach {
        it.sort()
    }

    println(list.contentDeepToString())

    val visited = Array(list.size){false}
    dfs(list, visited, 1)
}
private fun dfs(graph: Array<ArrayList<Int>>,visited: Array<Boolean> ,v: Int) {
    visited[v] = true
    println(v)

    for (node in graph[v]) {
        if (!visited[node]) {
            dfs(graph, visited, node)
        }
    }
}