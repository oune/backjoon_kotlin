import java.util.LinkedList
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val areas = readLine().split(" ").map { it.toInt() }
    val map = List(n) {
        readLine().split(" ").drop(1).map { it.toInt() - 1}
    }

    fun subset(): List<List<List<Int>>> {
        val res = mutableListOf<List<List<Int>>>()

        for (masking in 0 until (1 shl n)) {
            val a = mutableListOf<Int>()
            val b = mutableListOf<Int>()

            for (i in 0 until n) {
                if (masking and (1 shl i) != 0) {
                    a.add(i)
                } else {
                    b.add(i)
                }
            }

            res.add(listOf(a, b))
        }

        return res
    }

    var min = Int.MAX_VALUE
    val combs = subset().filter { it.first().size in 1 .. n / 2 }
    for (divides in combs) {
        val visited = BooleanArray(n) { false }
        var isTwo = true

        for (area in divides) {
            val start = area.first()

            val que = LinkedList<Int>()

            que.offer(start)
            visited[start] = true

            var count = 0
            while(que.isNotEmpty()) {
                val now = que.poll()
                count++

                for (next in map[now]) {
                    if (!visited[next] && next in area) {
                        que.offer(next)
                        visited[next] = true
                    }
                }
            }

            if (count != area.size) {
                isTwo = false
                break
            }
        }

        if (isTwo) {
            val a = divides[0].sumOf { areas[it] }
            val b = divides[1].sumOf { areas[it] }
            val difference = abs(a - b)

            min = min.coerceAtMost(difference)
        }
    }

    println(if (min == Int.MAX_VALUE) -1 else min)
}