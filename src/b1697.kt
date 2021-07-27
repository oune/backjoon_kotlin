import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    val visited = Array(100001) { 0 }
    val funList = listOf<(Int) -> Int>({ num -> num + 1}, { num -> num - 1 }, { num -> num * 2})

    print(bfs(n, k, n, visited, funList))
}

fun bfs(n: Int, k: Int, start: Int, visited: Array<Int>, funList: List<(Int) -> Int>): Int {
    val que : Queue<Int> = LinkedList<Int>()
    que.add(start)

    var res = 0
    while(que.isNotEmpty()) {
        val now = que.poll()

        if (k == now) {
            res = visited[now]
            break
        }

        funList.forEach {
            val moved = it(now)
            if (moved in 0..100000 && visited[moved] == 0) {
                que.add(moved)
                visited[moved] = visited[now] + 1
            }
        }
    }

    return res
}