import java.util.*

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

    fun bfs(limit:Int): Boolean {
        val que = LinkedList<Int>()
        val visited = BooleanArray(map.size) { false }

        que.offer(start)
        visited[start] = true

        while(que.isNotEmpty()) {
            val now = que.poll()

            if (now == end)
                return true

            for (next in map[now]) {
                if (next.cost < limit)
                    continue
                if (visited[next.pos])
                    continue

                que.offer(next.pos)
                visited[next.pos] = true
            }
        }

        return false
    }

    var left = 1
    var right = 1000000000
    while(left <= right) {
        val mid = (left + right) / 2

        if (bfs(mid)) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    println(right)
}