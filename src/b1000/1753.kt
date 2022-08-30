package test.b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (vertexCnt, edgeCnt) = readLine().split(" ").map{ it.toInt() }
    val start = readLine().toInt()
    val graph = Array(vertexCnt + 1) { mutableListOf<Pair<Int, Int>>() }

    repeat(edgeCnt) {
        val (a, b, cost) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, cost))
    }

    val inf = 200001
    val costs = IntArray(graph.size) { inf }
    costs[start] = 0

    val que = PriorityQueue ( compareBy<Pair<Int, Int>> { (_, cost) -> cost} )
    que.offer(Pair(start, 0))
    while(que.isNotEmpty()) {
        val (pos, cost) = que.poll()

        if (costs[pos] < cost)
            continue

        graph[pos].forEach { (to, newCost) ->
            if (costs[to] > costs[pos] + newCost) {
                costs[to] = costs[pos] + newCost
                que.offer(Pair(to, costs[to]))
            }
        }
    }

    val out = System.out.bufferedWriter()
    for (i in 1..costs.lastIndex) {
        if (costs[i] == inf)
            out.appendLine("INF")
        else
            out.appendLine(costs[i].toString())
    }
    out.flush()
}