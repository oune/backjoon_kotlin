import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val testcaseCnt = readLine().toInt()
    val sb = StringBuilder()

    repeat(testcaseCnt) {
        val (rowCnt, columnCnt) = readLine().split(" ").map { it.toInt() }
        data class State(val x:Int, val y:Int, val cost:Int)

        val map = List(rowCnt) {
            Array(columnCnt) {
                ArrayList<State>(5)
            }
        }

        repeat(rowCnt) { row ->
            val line = readLine().split(" ").map { it.toInt() }

            for (index in line.indices) {
                val cost = line[index]
                map[row][index].add(State(index + 1, row, cost))
                map[row][index + 1].add(State(index, row, cost))
            }
        }

        repeat(rowCnt - 1) { row ->
            val line = readLine().split(" ").map { it.toInt() }

            for (index in line.indices) {
                val cost = line[index]
                map[row][index].add(State(index, row + 1, cost))
                map[row + 1][index].add(State(index, row, cost))
            }
        }

        val que = PriorityQueue<State>( compareBy { it.cost } )
        val visited = List(rowCnt) {
            BooleanArray(columnCnt) { false }
        }
        val min = List(rowCnt) {
            IntArray(columnCnt) { Int.MAX_VALUE }
        }

        min[0][0] = 0
        que.offer(State(0, 0, 0))

        var ans = 0L
        var count = rowCnt * columnCnt
        while (que.isNotEmpty()) {
            val now = que.poll()

            if (visited[now.y][now.x])
                continue
            visited[now.y][now.x] = true

            ans += min[now.y][now.x]
            if (--count == 0)
                continue

            for (next in map[now.y][now.x]) {
                if (visited[next.y][next.x])
                    continue
                if (min[next.y][next.x] < next.cost)
                    continue

                min[next.y][next.x] = next.cost
                que.offer(State(next.x, next.y, next.cost))
            }
        }

        sb.appendLine(ans)
    }
    print(sb)
}