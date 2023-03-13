import java.util.LinkedList
import java.util.PriorityQueue

/*
다익스트라.
친구 3명이 사는 곳에서의 최단거리를 확인해야 하는데 양방향 그래프 이므로
집 기준으로 친구 3명이 사는
 */
fun main() = with(System.`in`.bufferedReader()) {
    val placeCnt = readLine().toInt()
    val (a, b, c) = readLine().split(" ").map { it.toInt() }
    val roadCnt = readLine().toInt()

    data class Road(val to:Int, val cost:Int)
    val map = List(placeCnt + 1) {
        LinkedList<Road>()
    }

    repeat(roadCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Road(to, cost))
        map[to].add(Road(from, cost))
    }

    fun dijkstra(start:Int): IntArray {
        val ans = IntArray(map.size) { Int.MAX_VALUE }
        ans[start] = 0

        val que = PriorityQueue<Road>( compareBy { it.cost } )
        que.offer(Road(start, 0))

        while(que.isNotEmpty()) {
            val (now, cost) = que.poll()

            if (ans[now] < cost)
                continue

            for ((next, newCost) in map[now]) {
                val nextCost = ans[now] + newCost
                if (ans[next] > nextCost) {
                    ans[next] = nextCost
                    que.offer(Road(next, nextCost))
                }
            }
        }

        return ans
    }

    val costA = dijkstra(a)
    val costB = dijkstra(b)
    val costC = dijkstra(c)

    val min = IntArray(map.size) { Int.MAX_VALUE }
    for (i in min.indices) {
        min[i] = costA[i].coerceAtMost(costB[i].coerceAtMost(costC[i]))
    }
    min[0] = 0

    println(min.withIndex().maxBy { it.value }.index)
}