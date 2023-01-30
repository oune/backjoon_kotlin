package b10000
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = readLine().split(" ").map { it.toInt() }

    fun bfs():Int {
        if (a == b)
            return 1;

        val que = LinkedList<Pair<Long, Int>>();
        que.offer(Pair(a.toLong(), 1))

        while(que.isNotEmpty()) {
            val now = que.poll()
            val nowCount = now.second + 1

            listOf(
                { num:Long -> num * 2},
                { num:Long -> num * 10 + 1}
            ).forEach {
                val moved = it(now.first)

                if (moved == b.toLong())
                    return nowCount;

                if (moved < b.toLong()) {
                    que.offer(Pair(moved, nowCount))
                }
            }
        }
        return -1
    }

    print(bfs())
}