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

    val sorted = stones.sortedWith( compareBy<List<Int>> { it.first() }.thenBy { it[1] })// sort by x
    for (i in sorted.indices) {
        val (x, y) = sorted[i]
        for (j in i - 1 downTo 0) {
            val (nx, ny) = sorted[j]

            val dx = abs(x - nx)
            val dy = abs(y - ny)

            if (dx <= 2 && dy <= 2) {
                val distance = sqrt(dx.toDouble() * dx + dy * dy)
                map[i].add(Pair(j, distance))
            } else if (dx > 2) {
                break
            }
        }
        for (j in i + 1 until sorted.size) {
            val (nx, ny) = sorted[j]

            val dx = abs(x - nx)
            val dy = abs(y - ny)

            if (dx <= 2 && dy <= 2) {
                val distance = sqrt(dx.toDouble() * dx + dy * dy)
                map[i].add(Pair(j, distance))
            } else if (dx > 2) {
                break
            }
        }
    }

    data class State(val idx: Int, val acc:Double)

    val que = PriorityQueue<State>( compareBy { it.acc })
    que.add(State(0, 0.0)) // (0, 0)은 항상 0번째 index에 위치

    val distances = DoubleArray(stones.size) { Double.MAX_VALUE }
    distances[0] = 0.0

    fun dijkstra(): Int {
        while(que.isNotEmpty()) {
            val (now, acc) = que.poll()

            if (distances[now] < acc)
                continue

            for ((next, nextCost) in map[now]) {
                val newDistance = acc + nextCost

                if (distances[next] > newDistance) {
                    distances[next] = newDistance
                    que.add(State(next, newDistance))
                }
            }
        }

        var min = Double.MAX_VALUE
        for (i in distances.indices) {
            val (_, y) = sorted[i]
            if (y == f) {
                min = min.coerceAtMost(distances[i])
            }
        }

        return if (min == Double.MAX_VALUE) -1 else min.roundToInt()
    }

    println(dijkstra())
}