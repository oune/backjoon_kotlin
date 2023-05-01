/*
* 벨만 포드
*
* 출력 초과(38%)
* 비용의 범위가 Int 를 벗어 날 수 있음. 따라서 비용의 배열은 Long 타입이 되어야함.
* */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    data class Edge(val from:Int, val to:Int, val cost:Int)
    val edges = List(m) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        Edge(from, to, cost)
    }

    val inf = Int.MAX_VALUE.toLong()
    val costs = LongArray(n + 1) {
        inf
    }

    costs[1] = 0
    repeat(n - 1) {
        for ((from, to, cost) in edges) {
            if (costs[from] == inf)
                continue

            val newCost = costs[from] + cost
            if (costs[to] > newCost)
                costs[to] = newCost
        }
    }

    var isCycle = false
    for ((from, to, cost) in edges) {
        if (costs[from] == inf)
            continue

        if (costs[to] > costs[from] + cost)
            isCycle = true
    }

    if (isCycle) {
        println("-1")
    } else {
        println(costs.drop(2).map { if (it == inf) -1 else it }.joinToString("\n"))
    }
}