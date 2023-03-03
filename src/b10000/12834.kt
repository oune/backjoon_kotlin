import java.util.LinkedList
import java.util.PriorityQueue

/*
플로이드 워셜인가? 싶지만. V = 1000
n^3 으로는 10^9 >> 10초
다익스트라 시간 복잡도 nlogn
n log n * 사람수 >> 10^3 * 3 * 10^2 * 2 = 6 * 10^5

양방향 그래프에서 최단 경로는 여기서 저기로 가는 경로와 저기서 여기로 오는 경로가 동일함
따라서 여러 집에서 각각의 장소를 이동하는 경로를 찾는 것보다
각각의 장소로 오는 비용을 구해두고 나중에 집에서 가는 비용을 확인하는것이 더욱 효율적임.
45분

오래 걸린 이유 : 다잌스트라 초기화 할때 맨처음 노드 0으로 초기화 안함..
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (_, v, e) = readLine().split(" ").map { it.toInt() }
    val (a, b) = readLine().split(" ").map { it.toInt() }
    val homes = readLine().split(" ").map { it.toInt() }

    val map = List(v + 1) {
        LinkedList<Pair<Int, Int>>()
    }

    repeat(e) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Pair(to, cost))
        map[to].add(Pair(from, cost))
    }

    fun dijkstra (start: Int): IntArray {
        val ans = IntArray(map.size) { Int.MAX_VALUE }
        val que = PriorityQueue<Pair<Int, Int>>( compareBy { it.second })
        que.offer(Pair(start, 0))
        ans[start] = 0

        while(que.isNotEmpty()) {
            val (now, nowCost) = que.poll()

            if (ans[now] < nowCost)
                continue

            for ((next, nextCost) in map[now]) {
                val newCost = nowCost + nextCost
                if (ans[next] > newCost) {
                    ans[next] = newCost
                    que.offer(Pair(next, newCost))
                }
            }
        }

        return ans
    }
    val handleMax = { it:Int -> if (it == Int.MAX_VALUE) -1 else it }
    val kistShort = dijkstra(a).map(handleMax)
    val seeAlShort = dijkstra(b).map(handleMax)

    val sum = homes.sumOf {
        val kist = kistShort[it]
        val seeAl = seeAlShort[it]
        kist + seeAl
    }

    println(sum)
}