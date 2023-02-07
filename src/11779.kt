import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val citiesCnt = readLine().toInt()
    val busCnt = readLine().toInt()

    data class Bus(val pos: Int, val cost: Int)

    val map = Array(citiesCnt + 1) {
        LinkedList<Bus>()
    }

    repeat(busCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].offer(Bus(to, cost))
    }

    val (start, end) = readLine().split(" ").map { it.toInt() }

    val max = 100001
    val ans = Array(1001) {
        Bus(it, 100001)
    }
    val que = PriorityQueue<Bus>( compareBy { it.cost } )

    ans[start] = Bus(start, 0)
    que.offer(Bus(start, 0))

    while (que.isNotEmpty()) {
        val now = que.poll()

        map[now.pos].filter { moved ->
            ans[moved.pos].cost > ans[now.pos].cost + moved.cost
        }.forEach { moved ->
            ans[moved.pos] = Bus(now.pos, ans[now.pos].cost + moved.cost)
            que.offer(Bus(moved.pos, ans[now.pos].cost + moved.cost))
        }
    }

    var res = listOf(end)
    var now = end
    while (now != start) {
        val next = ans[now].pos
        res = listOf(next) + res
        now = next
    }

    println(ans.filter { it.cost != 100001 }.maxOf { it.cost })
    println(res.size)
    println(res.joinToString(" "))

}