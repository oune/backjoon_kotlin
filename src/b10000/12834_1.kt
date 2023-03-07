import java.util.LinkedList
import java.util.PriorityQueue

/*
다익스트라
양방향 그래프에서 a, b에서 c로 가는 최단경로는 c 에서 a, b 로 가는 최단 경로와 같다.
따라서 주어진 그래프로 다익스트라를 이용하여 최단경로를 구하고, 비용을 확인한다.
*/
fun main() = with(System.`in`.bufferedReader()) {
    val (n, v, e) = readLine().split(" ").map { it.toInt() }
    val (kPos, cPos) = readLine().split(" ").map { it.toInt() - 1}
    val homes = readLine().split(" ").map { it.toInt() - 1}
    val map = List(v) {
        LinkedList<Pair<Int, Int>>()
    }

    repeat(e) {
        val (_from, _to, cost) = readLine().split(" ").map { it.toInt() }
        val from = _from - 1
        val to = _to - 1

        map[from].add(Pair(to, cost))
        map[to].add(Pair(from, cost))
    }

    fun dijkstra(start:Int): IntArray {
        val ans = IntArray(v) { Int.MAX_VALUE }
        val que = PriorityQueue<Pair<Int, Int>>( compareBy { it.second })

        ans[start] = 0
        que.offer(Pair(start, 0))

        while(que.isNotEmpty()) {
            val (pos, cost) = que.poll()

            if (ans[pos] < cost)
                continue

            for (next in map[pos]) {
                val newCost = ans[pos] + next.second

                if (ans[next.first] > newCost) {
                    ans[next.first] = newCost
                    que.offer(Pair(next.first, newCost))
                }
            }
        }

        return ans
    }

    fun IntArray.handleMax(): List<Int> {
        return this.map {
            if (it == Int.MAX_VALUE) -1 else it
        }
    }

    val k = dijkstra(kPos).handleMax()
    val c = dijkstra(cPos).handleMax()

    val sum = homes.sumOf { k[it] + c[it]}
    println(sum)
}