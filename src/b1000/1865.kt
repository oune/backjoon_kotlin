/*
* 벨만 포드
* 음수 사이클이 존재하는지만 확인
* 그렇기에 inf 를 확인하지 않고 전체를 순회
*
* ❌(32%)
* from, to 가 같은 간선이 존재 하기 때문에 0으로 초기화 하는것은 map초기화 한 이후에 하여야 한다.
*
* 시간 초과(91%)
* 플로이드 워샬의 시간복잡도는 n^3, 테스트 케이스의 개수는 5개로
* n = 500 이므로 전체 10초의 시간이 필요로 하다 따라서 플로이드 워샬이 아닌 벨만 포드를 이용하여 풀어야 한다.
* */
fun main() {
    data class Edge(val from:Int, val to:Int, val cost:Int)

    val tc = readln().toInt()
    val ans = List(tc) {
        val (n, m, w) = readln().split(" ").map { it.toInt() }

        val edges = List(m) {
            val (from, to, cost) = readln().split(" ").map { it.toInt() }
            listOf(Edge(from, to, cost), Edge(to, from, cost))
        }.flatten() + List(w) {
            val (from, to, cost) = readln().split(" ").map { it.toInt() }
            Edge(from, to, -cost)
        }

        val costs = IntArray(n + 1) { 501 * 10000 }

        repeat(n - 1) {
            for ((from, to, cost) in edges) {
                val newCost = costs[from] + cost
                if (costs[to] > newCost)
                    costs[to] = newCost
            }
        }

        var isCycle = false
        for ((from, to, cost) in edges) {
            if (costs[to] > costs[from] + cost)
                isCycle = true
        }

        if (isCycle) "YES" else "NO"
    }
    print(ans.joinToString("\n"))
}