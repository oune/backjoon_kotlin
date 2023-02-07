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

    val max = Int.MAX_VALUE
    val ans = IntArray(citiesCnt + 1) { max }
    val from = IntArray(citiesCnt + 1) { 0 }
    val que = PriorityQueue<Bus>( compareBy { it.cost } )

    ans[start] = 0
    que.offer(Bus(start, 0))

    while (que.isNotEmpty()) {
        val now = que.poll()

        if(ans[now.pos] < now.cost)
            continue;

        for (moved in map[now.pos]) {
            if (ans[moved.pos] > ans[now.pos] + moved.cost) {
                ans[moved.pos] = ans[now.pos] + moved.cost
                que.offer(Bus(moved.pos, ans[moved.pos]))
                from[moved.pos] = now.pos
            }
        }
    }

    var res = listOf(end)
    var now = end
    while (now != start) {
        val next = from[now]
        res = listOf(next) + res
        now = next
    }

    println(ans[end])
    println(res.size)
    println(res.joinToString(" "))
}