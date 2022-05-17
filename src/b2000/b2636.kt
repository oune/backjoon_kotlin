package test.b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, _) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toMutableList()
    }

    var isContinue = true
    var outTime = 0
    while (isContinue) {
        val que = LinkedList<Pair<Int, Int>>()
        que.offer(Pair(0, 0))

        val time = outTime
        isContinue = false

        val visited = Array(arr.size) {
            BooleanArray(arr[it].size) { false }
        }

        while (que.isNotEmpty()) {
            val (x, y) = que.poll()

            if (visited[y][x]) {
                continue
            }

            visited[y][x] = true

            if (arr[y][x] == 1) {
                arr[y][x] = time - 1

                if (outTime == time)
                    outTime--

                isContinue = true
            } else {
                listOf (
                    {x:Int, y:Int -> Pair(x + 1, y)},
                    {x:Int, y:Int -> Pair(x - 1, y)},
                    {x:Int, y:Int -> Pair(x, y + 1)},
                    {x:Int, y:Int -> Pair(x, y - 1)}
                ).forEach {
                    val moved = it(x, y)

                    if (visited.isInIndices(moved.first, moved.second) && !visited[moved.second][moved.first]) {
                        que.offer(Pair(moved.first, moved.second))
                    }
                }
            }
        }
    }

    val min = arr.fold(0) { acc, list ->
        acc.coerceAtMost(list.fold(0) { innerAcc, i ->
            innerAcc.coerceAtMost(i)
        })
    }
    val cnt = if (min != 0) {
        arr.fold(0) { acc, list ->
            acc + list.count {
                it == min
            }
        }
    } else
        0

    println(-min)
    println(cnt)
}

fun Array<BooleanArray>.isInIndices(x:Int, y:Int): Boolean{
    if (y in this.indices && x in this[y].indices) {
        return true
    }
    return false
}