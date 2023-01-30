package b1000
import java.util.*

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

    fun findMaxNode():Int {
        val visited = BooleanArray(map.size) { false }
        visited[1] = true

        var max = 0;
        var maxNode = 1
        fun findMaxNode(node:Int, acc:Int) {
            if (acc > max) {
                max = acc
                maxNode = node
            }

            map[node].filter { !visited[it.to] }.forEach { (to, price) ->
                visited[node] = true
                findMaxNode(to, acc + price)
            }
        }

        findMaxNode(1, 0)
        return maxNode
    }

    val maxNode = findMaxNode()
    var max = 0

    fun dfs(node:Int, visited:BooleanArray, acc:Int) {
        if (acc > max)
            max = acc

        map[node].filter { !visited[it.to] }.forEach { (to, price) ->
            visited[node] = true
            dfs(to, visited, acc + price)
        }
    }
    val visited = BooleanArray(map.size) { false }
    visited[maxNode] = true
    dfs(maxNode, visited, 0)

    println(max)
}