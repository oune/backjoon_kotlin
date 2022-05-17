package test.b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, _) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toMutableList()
    }

    val visited = Array(arr.size) {
        BooleanArray(arr[it].size) { false }
    }

    val que = LinkedList<Cheese>()
    que.offer(Cheese(0, 0, 0))

    while (que.isNotEmpty()) {
        val (x, y, time) = que.poll()
        visited[y][x] = true

        if (arr[y][x] == time) {
            arr[y][x] = time + 1
        }

        listOf (
            {x:Int, y:Int -> Pair(x + 1, y)},
            {x:Int, y:Int -> Pair(x - 1, y)},
            {x:Int, y:Int -> Pair(x, y + 1)},
            {x:Int, y:Int -> Pair(x, y - 1)}
        ).forEach {
            val moved = it(x, y)

            if (visited.isInIndices(moved.first, moved.second) && !visited[moved.second][moved.first]) {
                que.offer(Cheese(moved.first, moved.second, arr[y][x]))
            }
        }
    }

    println(arr.contentDeepToString())
}

fun Array<BooleanArray>.isInIndices(x:Int, y:Int): Boolean{
    if (y in this.indices && x in this[y].indices) {
        return true
    }
    return false
}

private data class Cheese(val x:Int, val y:Int, val time:Int)