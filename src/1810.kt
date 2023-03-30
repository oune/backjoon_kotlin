import java.util.LinkedList
import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.sqrt

/*
다익스트라
거리를 계산하고 거리 내의 점들을 움직임

시간초과
전부 순회하고 확인하는 것이 아니라 중간에 확인하도록 변경
갈수 있는 거리를 매번 확인하는 것이 아니라 미리 확인하는 것으로 변경

 */

fun main() {
    val (n, f) = readln().split(" ").map { it.toInt() }
    val stones = listOf(listOf(0, 0)) + List(n) {
        readln().split(" ").map { it.toInt() }
    }
    val map = List(stones.size) {
        LinkedList<Pair<Int, Double>>()
    }

    for (i in map.indices) {
        val (x, y) = stones[i]
        for (j in stones.indices) {
            if (i == j)
                continue

            val (nx, ny) = stones[j]
            val dx = abs(x - nx)
            val dy = abs(y - ny)

            if (dx <= 2 && dy <= 2) { // jump possible
                val distance = sqrt(dx.toDouble() * dx + dy * dy)
                map[i].add(Pair(j, distance))
            }
        }
    }

    data class State(val idx: Int, val acc:Double)

    val que = PriorityQueue<State>( compareBy { it.acc })
    que.add(State(0, 0.0))

    val distances = DoubleArray(stones.size) { Double.MAX_VALUE }
    distances[0] = 0.0

    fun dijkstra(): Int {
        while(que.isNotEmpty()) {
            val (now, acc) = que.poll()
            val (x, y) = stones[now]

            if (distances[now] < acc)
                continue

            for ((next, nextCost) in map[now]) {
                val newDistance = acc + nextCost

                if (distances[next] > newDistance) {
                    distances[next] = newDistance
                    que.add(State(next, newDistance))

                    if (stones[next][1] == f) {
                        return newDistance.roundToInt()
                    }
                }
            }
        }
        return -1
    }

    println(dijkstra())
}