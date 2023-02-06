import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (areaCnt, searchRange, roadCnt) = readLine().split(" ").map { it.toInt() }
    val items = listOf(0) + readLine().split(" ").map { it.toInt() }

    data class Edge(val pos:Int, val cost:Int)
    val graph = Array(areaCnt + 1) {
        LinkedList<Edge>()
    }
    repeat(roadCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, cost))
        graph[to].add(Edge(from, cost))
    }

    val distance = Array(areaCnt + 1) { Int.MAX_VALUE }


}