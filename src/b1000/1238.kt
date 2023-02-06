import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (students, roads, target) = readLine().split(" ").map { it.toInt() }

    data class Road(val to:Int, val cost:Int)

    val map = Array(students + 1) {
        LinkedList<Road>()
    }
    val reversedMap = Array(students + 1) {
        LinkedList<Road>()
    }

    repeat(roads) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].offer(Road(to, cost))
        reversedMap[to].offer(Road(from, cost))
    }

    fun Array<LinkedList<Road>>.dijkstra(target: Int): IntArray {
        val ans = IntArray(students + 1) { Int.MAX_VALUE }
        val que = PriorityQueue<Road>( compareBy { it.cost } )

        ans[target] = 0
        que.offer(Road(target, 0))

        while(que.isNotEmpty()) {
            val now = que.poll()

            this[now.to].filter { moved ->
                ans[moved.to] > ans[now.to] + moved.cost
            }.forEach { moved ->
                ans[moved.to] = ans[now.to] + moved.cost
                que.offer(Road(moved.to, ans[moved.to]))
            }
        }

        return ans
    }

    val ans1 = map.dijkstra(target)
    val ans2 = reversedMap.dijkstra(target)

    fun IntArray.plusEach(arr: IntArray):IntArray {
        val res = this.clone()

        for (i in this.indices) {
            res[i] = if (this[i] == Int.MAX_VALUE) 0 else this[i] + if (arr[i] == Int.MAX_VALUE) 0 else arr[i]
        }

        return res
    }

    println(ans1.plusEach(ans2).max())
}