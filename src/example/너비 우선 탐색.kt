package test.example

fun main() {
    val (points, lines) = readLine()!!.split(" ").map { it.toInt() }

    val list = Array<MutableList<Int>>(points + 1) { mutableListOf() }

    repeat(lines) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        list[a] += b
        list[b] += a
    }
    list.forEach {
        it.sort()
    }

    bfs(list, 1)
}

fun bfs(graph: Array<MutableList<Int>>, start: Int) {
    val que = mutableListOf<Int>()
    // Linked 리스트가 리스트 구조 이기때문에 도욱 효율성이 좋음 LinkedList<Int>()
    val visited = Array(graph.size){ false }

    que.add(start)
    visited[start] = true

    while(que.isNotEmpty()) {
        val t = que.first()
        que.removeAt(0)
        print(t)

        graph[t].forEach {
            if (!visited[it]) {
                que.add(it)
                visited[it] = true
            }
        }
    }
}