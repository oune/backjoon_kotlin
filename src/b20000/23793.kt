import java.util.LinkedList
import java.util.PriorityQueue

/*
다익스트라
특정 노드를 지나지 않은 경우를 처리하는 다익스트라
입력을 받을때 y를 포함하지 않는 맵을 미리 만들어서 처리

틀렸습니다. (63%)
두번째 예제 확인하지 않음

시간초과 (41%)
y확인을 반복문 안에서 처리
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val inputs = List(m) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        Triple(from, to, cost)
    }

    val (x, y, z) = readLine().split(" ").map { it.toInt() }

    val map = List(n + 1) {
        LinkedList<Pair<Int, Int>>()
    }
    val mapNoY = List(n + 1) {
        LinkedList<Pair<Int, Int>>()
    }

    for ((from, to, cost) in inputs) {
        if (to != y)
            mapNoY[from].add(Pair(to, cost))
        map[from].add(Pair(to, cost))
    }

    fun  List<LinkedList<Pair<Int, Int>>>.dijkstra(start:Int): IntArray {
        val ans = IntArray(map.size) { Int.MAX_VALUE }
        ans[start] = 0

        val que = PriorityQueue<Pair<Int, Int>>( compareBy { it.second })
        que.offer(Pair(start, 0))

        while(que.isNotEmpty()) {
            val (now, acc) = que.poll()

            if (ans[now] < acc)
                continue

            for ((next, cost) in this[now]) {
                val newCost = acc + cost

                if (ans[next] > newCost) {
                    ans[next] = newCost
                    que.offer(Pair(next, newCost))
                }
            }
        }

        return ans
    }

    val shortestX = map.dijkstra(x)
    val shortestY = map.dijkstra(y)
    val shortestXWithoutY = mapNoY.dijkstra(x)

    val xToZ = if (shortestXWithoutY[z] != Int.MAX_VALUE) shortestXWithoutY[z] else -1
    val xtoY = if (shortestX[y] != Int.MAX_VALUE) shortestX[y] else -1
    val ytoZ = if (shortestY[z] != Int.MAX_VALUE) shortestY[z] else -1
    val xtoYtoZ = if (xtoY == -1) -1 else if (ytoZ == -1) -1 else xtoY + ytoZ

    println("$xtoYtoZ $xToZ")
}