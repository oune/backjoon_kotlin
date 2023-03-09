import java.util.*
import kotlin.math.abs

/*
다익스트라
간선의 비용이 주어지지 않고 정점의 차이가 비용으로 하는 다익스트라
다익스트라에 저장하는값이 누적비용이 아니라 최대 비용이 저장되고, 가장 작은 최대의 비용으로 갱신되도록함.
시간초과 이유
아니 que 를 우선순위큐를 쓰지 않았기 때문에
n^2 복잡도가 발생함. 우선순위큐 사용으로 (v + e) log v 으로 성공
 */
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = List(size) {
        readLine().split(" ").map { it.toInt() }
    }

    data class State(val x:Int, val y:Int, val cost:Int)
    val que = PriorityQueue<State>( compareBy { it.cost })
    que.add(State(0, 0, 0))

    val ans = List(size) { IntArray(size) { Int.MAX_VALUE } }
    ans[0][0] = 0

    val moves = listOf(
        { from:State -> Pair(from.x + 1, from.y) },
        { from:State -> Pair(from.x - 1, from.y) },
        { from:State -> Pair(from.x, from.y + 1) },
        { from:State -> Pair(from.x, from.y - 1) },
    )

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (ans[now.y][now.x] < now.cost)
            continue

        for (move in moves) {
            val (x, y) = move(now)
            if (y in map.indices && x in map[y].indices) {
                val cost = ans[now.y][now.x].coerceAtLeast(abs(map[y][x] - map[now.y][now.x]))

                if (ans[y][x] > cost) {
                    ans[y][x] = cost
                    que.offer(State(x, y, cost))
                }
            }
        }
    }

    println(ans.last().last())
}