package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    data class Fish(val size:Int, val x: Int, val y: Int)

    val mapSize = readLine().toInt()
    val fishes = LinkedList<Fish>()
    val space = Array(mapSize) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var baby = Shark(2, 0, 0)
    space.forEachIndexed {
        idx, list ->
            list.forEachIndexed {
                listIdx, num ->
                if (num == 9)
                    baby = Shark(2, listIdx, idx)
                else if (num > 0) {
                    fishes.offer(Fish(num, listIdx, idx))
                }
            }
    }

    var time = 0
    while(fishes.isNotEmpty()) {
        val eatable = fishes.filter {
            it.size < baby.size
        }

        if (eatable.isEmpty()) {
            break
        }

        val res = eatable.map {
            val (_, x, y) = it // 도착점과 시작점 변경 필요
            getDistance(x, y, baby, space)
        }.filter{ it.time != -1 }.sortedBy { it.time }

        if (res.isEmpty())
            break

        val shortest = res.first()
        time += shortest.time
    }

    print(time)
}

fun getDistance(x:Int, y:Int, baby:Shark, space:Array<IntArray>) :PositionState{
    val visited = Array(space.size) { BooleanArray(space[it].size) { false } }
    val que = LinkedList<PositionState>()
    que.offer(PositionState(baby.x, baby.y, 0))

    while(que.isNotEmpty()) {
        val now = que.poll()
        visited[now.y][now.x] = true

        listOf(
            { x:Int, y:Int -> Pair(x, y) },
            { x:Int, y:Int -> Pair(x - 1, y) },
            { x:Int, y:Int -> Pair(x, y + 1) },
            { x:Int, y:Int -> Pair(x, y - 1) }
        ).forEach {
            val moved = it(now.x, now.y)

            if (moved.first in visited.indices && moved.second in visited[moved.first].indices) {
                if (!visited[moved.second][moved.first])
                    if (space[moved.second][moved.first] <= baby.size)
                        if (moved.first == x && moved.second == y) {
                            baby.eat()
                            return PositionState(x, y, now.time)
                        } else
                            que.offer(PositionState(moved.first, moved.second, now.time + 1))
            }
        }
    }

    return -1
}

data class PositionState(val x:Int, val y:Int, val time:Int)

class Shark(var size: Int, val x:Int, val y:Int) {
    private var count = 0

    fun eat() {
        count++

        if (count == size) {
            size++
            count = 0
        }
    }
}