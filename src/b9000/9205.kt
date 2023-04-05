import java.util.LinkedList
import kotlin.math.abs

/*
그래프 탐색
거리제한이 있는 상황에서 도착 할 수 있는지 확인
 */
fun main() {
    val tcCnt = readln().toInt()
    val ans = List(tcCnt) {
        val placeCnt = readln().toInt() + 2

        val places = List(placeCnt) {
            val (first, second) = readln().split(" ").map { it.toInt() }
            Pair(first, second)
        }

        val que = LinkedList<Int>()
        que.add(0)

        val visited = BooleanArray(placeCnt) { false }
        visited[0] = true

        while(que.isNotEmpty()) {
            val now = places[que.poll()]

            for (i in places.indices) {
                if (visited[i])
                    continue

                val next = places[i]
                val dx = abs(now.first - next.first)
                val dy = abs(now.second - next.second)

                if (dx + dy > 20 * 50)
                    continue

                que.add(i)
                visited[i] = true
            }
        }

        if (visited.last()) "happy" else "sad"
    }

    println(ans.joinToString("\n"))
}