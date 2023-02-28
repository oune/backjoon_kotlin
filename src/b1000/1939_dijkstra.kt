import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    data class State(val pos:Int, val cost:Int)
    val map = List(n + 1) {
        LinkedList<State>()
    }

    repeat(m) {
        val (from, to, limit) = readLine().split(" ").map { it.toInt() }
        map[from].add(State(to, limit))
        map[to].add(State(from, limit))
    }
    val (start, end) = readLine().split(" ").map { it.toInt() }

    val ans = IntArray(n + 1) { 0 }
    val que = LinkedList<State>()
    ans[start] = Int.MAX_VALUE
    que.offer(State(start, ans[start]))

    while(que.isNotEmpty()) {
        val (now, limit) = que.poll()

        if (ans[now] > limit)
            continue

        for (next in map[now]) {
            val newLimit = limit.coerceAtMost(next.cost)

            if (ans[next.pos] < newLimit) {
                ans[next.pos] = newLimit
                que.offer(State(next.pos, newLimit))
            }
        }
    }
    println(ans[end])
}