package test.b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, _) = readLine().split(" ").map{ it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toMutableList()
    }

    val mainQue = LinkedList<Cheese>()
    mainQue.offer(Cheese(0, 0, 0))
    while (mainQue.isNotEmpty()) {
        val que = LinkedList<Cheese>()

        val time = mainQue.peek().time

        if (mainQue.isNotEmpty()) {
            que.offer(Cheese(0, 0, mainQue.pop().time))
            mainQue.clear()
        }


        val visited = Array(arr.size) {
            BooleanArray(arr[it].size) { false }
        }
        while (que.isNotEmpty()) {
            val (x, y, _) = que.poll()

            if (visited[y][x]) {
                continue
            }

            visited[y][x] = true

            if (arr[y][x] == 1) {
                arr[y][x] = time - 1
                mainQue.offer(Cheese(x, y, time - 1))
            } else {
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
        }
    }

    val max = - arr.fold(0) { acc, list ->
        acc.coerceAtMost(list.fold(0) { innerAcc, i ->
            innerAcc.coerceAtMost(i)
        })
    }
    val cnt = arr.fold(0) { acc, list ->
        acc + list.count {
            it == - max
        }
    }

    if (max != 0) {
        println(max)
        println(cnt)
    } else {
        println(0)
        println(0)
    }
}

fun Array<BooleanArray>.isInIndices(x:Int, y:Int): Boolean{
    if (y in this.indices && x in this[y].indices) {
        return true
    }
    return false
}

private data class Cheese(val x:Int, val y:Int, val time:Int)