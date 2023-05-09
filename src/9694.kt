import java.util.*
/*
* 다익스트라
* */
fun main() {
    val testcaseCnt = readln().toInt()
    repeat(testcaseCnt) { testcase ->
        var st = StringTokenizer(readln())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        val map = List(m) {
            mutableListOf<Pair<Int, Int>>()
        }

        repeat(n) {
            st = StringTokenizer(readln())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val cost = st.nextToken().toInt()
            map[from].add(Pair(to, cost))
            map[to].add(Pair(from, cost))
        }

        val costs = IntArray(m) { Int.MAX_VALUE }
        val froms = IntArray(m) { -1 }
        costs[0] = 0

        val que = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        que.add(Pair(0, 0))

        while(que.isNotEmpty()) {
            val (now, cost) = que.poll()

            if (costs[now] < cost)
                continue

            for ((next, nextCost) in map[now]) {
                val newCost = nextCost + costs[now]

                if (costs[next] > newCost) {
                    costs[next] = newCost
                    froms[next] = now
                    que.add(Pair(next, newCost))
                }
            }
        }

        val ans = mutableListOf<Int>()
        if (costs.last() != Int.MAX_VALUE) {
            var idx = costs.lastIndex
            while (idx != -1) {
                ans.add(idx)
                idx = froms[idx]
            }
        } else {
            ans.add(-1)
        }

        println("Case #${testcase + 1}: ${ans.reversed().joinToString(" ")}")
    }
}