import java.util.PriorityQueue
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val map = List(n) {
        readln().split(" ").mapIndexed { index, s ->  Pair(index, s.toInt())}.filter { (_, cost) -> cost != 0 }
    }

    fun dfs(): Int {
        var min = Int.MAX_VALUE
        val visited = BooleanArray(n) { false }

        fun dfs(idx:Int, count:Int, acc:Int) {
            for ((next, cost) in map[idx]) {
                if (next == 0 && count == n) {
                    min = min(min, acc + cost)
                    return
                }

                if (!visited[next]) {
                    visited[next] = true
                    dfs(next, count + 1, acc + cost)
                    visited[next] = false
                }
            }
        }

        visited[0] = true
        dfs(0, 1,0)

        return min
    }

    println(dfs())
}