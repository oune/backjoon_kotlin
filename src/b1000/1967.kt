package b1000import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val nodeCount = readLine().toInt()

    data class Edge(val to:Int, val price: Int)

    val map = Array(nodeCount + 1) {
        mutableListOf<Edge>()
    }

    repeat(nodeCount - 1) {
        val (parent, child, price) = readLine().split(" ").map { it.toInt() }

        map[parent].add(Edge(child, price))
        map[child].add(Edge(parent, price))
    }

    var max = 0
    fun dfs(node:Int, visited:BooleanArray, acc:Int) {
        if (acc > max)
            max = acc

        map[node].filter { !visited[it.to] }.forEach { (to, price) ->
            visited[node] = true
            dfs(to, visited, acc + price)
        }
    }

    map.withIndex().filter { it.value.size == 1 }.forEach {
        val idx = it.index
        val visited = BooleanArray(map.size) { false }
        visited[idx] = true
        dfs(idx, visited, 0)
    }

    println(max)
}