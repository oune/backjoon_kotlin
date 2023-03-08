import java.util.LinkedList

/*
다익스트라
특정 지점를 지나는 최단 경로가 존재 하는가?
우선 시작점으로 부터 최단 비용을 계산함. >> 전체 최단 경로 확인
특정 지점으로 부터 도착지 까지의 최소비용을 계산
시작 에서 특점지점까지의 비용과 특정 지먼으로 부터 도착지 까지의 최소비용의 합이 전체 최단 경로 라면
특점 지점을 지나는 최단 경로가 존재.

틀린이유 다익스트라 작성하는 과정에서 수정안한 부분이 있었음. 꼼꼼하지 못했던게 문제
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (v, e, p) = readLine().split(" ").map { it.toInt() }
    val map = List(v + 1) {
        LinkedList<Pair<Int, Int>>()
    }
    repeat(e) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Pair(to, cost))
        map[to].add(Pair(from, cost))
    }

    fun dijkstra(start:Int): IntArray {
        val ans = IntArray(v + 1) { Int.MAX_VALUE }
        val que = LinkedList<Pair<Int, Int>>()

        ans[start] = 0
        que.offer(Pair(start, 0))

        while(que.isNotEmpty()) {
            val (now, cost) = que.poll()

            if (ans[now] < cost)
                continue

            for ((next, nextCost) in map[now]) {
                val newCost = ans[now] + nextCost
                if (ans[next] > newCost) {
                    ans[next] = newCost
                    que.offer(Pair(next, newCost))
                }
            }
        }

        return ans
    }

    val total = dijkstra(1)
    val mid = dijkstra(p)

    val startToLast = total[v]
    val startToMid = total[p]
    val midToLast = mid[v]

    if (startToLast == startToMid + midToLast) {
        println("SAVE HIM")
    } else {
        println("GOOD BYE")
    }
}