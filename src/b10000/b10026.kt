import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val arr = Array(size) {
        readLine().toCharArray()
    }
    val arr2 = Array(size) {
        val list = mutableListOf<Char>()
        arr[it].forEach { char ->
            if (char == 'R')
                list.add('G')
            else
                list.add(char)
        }
        list.toCharArray()
    }
    val visited = Array(size){Array(size){false} }
    val visited2 = Array(size){Array(size){false} }

    var count = 0
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (!visited[i][j]) {
                bfs(arr, visited, j, i)
                count++
            }
        }
    }

    var count2 = 0
    for (i in arr2.indices) {
        for (j in arr2[i].indices) {
            if (!visited2[i][j]) {
                bfs(arr2, visited2, j, i)
                count2++
            }
        }
    }

    print("$count $count2")
}

private val moveList = listOf(
    {x: Int, y: Int -> Pair(x + 1, y)},
    {x: Int, y: Int -> Pair(x - 1, y)},
    {x: Int, y: Int -> Pair(x, y + 1)},
    {x: Int, y: Int -> Pair(x, y - 1)}
)

private fun bfs(arr:Array<CharArray>, visited:Array<Array<Boolean>> , x: Int, y: Int) {
    val que = LinkedList<Pair<Int, Int>>()
    que.offer(Pair(x, y))
    visited[y][x] = true

    while (que.isNotEmpty()) {
        val t = que.poll()

        for (move in moveList) {
            val moved = move(t.first, t.second)

            if (moved.first in arr.indices && moved.second in arr[0].indices) {
                if (arr[t.second][t.first] == arr[moved.second][moved.first]) {
                    if (!visited[moved.second][moved.first]) {
                        que.offer(moved)
                        visited[moved.second][moved.first] = true
                    }
                }
            }
        }
    }
}