package b7000

import java.util.*

data class Pos(val x:Int, val y:Int, val z:Int, val day:Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n, h) = readLine().split(" ").map { it.toInt() }

    val que = LinkedList<Pos>()

    val arr = Array(h) {
        Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }
    }
    arr.forEachIndexed { i, array ->
        array.forEachIndexed { j, ints ->
            ints.forEachIndexed { k, num ->
                if (num == 1)
                    que.offer(Pos(k, j, i, 0))
            }
        }
    }

    val visited = Array(h) {
        Array(n) {
            BooleanArray(m) { false }
        }
    }

    val directions =
        listOf(Triple(1, 0, 0)
            , Triple(-1, 0, 0)
            , Triple(0, 1, 0)
            , Triple(0, -1, 0)
            , Triple(0, 0, 1)
            , Triple(0, 0, -1))


    var max = 0
    while (que.isNotEmpty()) {
        val (x, y, z, day) = que.poll()

        if (z < 0 || z >= h)
            continue
        if (y < 0 || y >= n)
            continue
        if (x < 0 || x >= m)
            continue
        if (visited[z][y][x])
            continue
        if (arr[z][y][x] == -1)
            continue

        arr[z][y][x] = 1
        visited[z][y][x] = true
        if (day > max)
            max = day

        directions.forEach {
            que.offer(Pos(x + it.first, y + it.second, z + it.third, day + 1))
        }
    }

    if (arr.all { arrArr -> arrArr.all { intArr -> intArr.all { it == 1 || it == -1 } } }) {
        print(max)
    } else {
        print(-1)
    }
}