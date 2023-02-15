fun main() = with(System.`in`.bufferedReader()) {
    val nodeCount = readLine().toInt()

    data class Edge(val to:Int, val price: Int)

    val map = Array(nodeCount + 1) {
        mutableListOf<Edge>()
    }

    repeat(nodeCount) {
        val list = readLine().split(" ").map { it.toInt() }
        val id = list.first()
        val chunked = list.drop(1).chunked(2).filter { it.size != 1 }

        chunked.forEach { (to, cost) ->
            map[id].add(Edge(to, cost))
        }
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