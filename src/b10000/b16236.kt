package test.b10000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val mapSize = readLine().toInt()
    val space = Array(mapSize) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    var baby = Shark(2, 0, 0)
    space.forEachIndexed { idx, list ->
        list.forEachIndexed { listIdx, num ->
            if (num == 9) {
                baby = Shark(2, listIdx, idx)
                space[idx][listIdx] = 0
            }
        }
    }
    data class State(val point: Point, val time:Int)

    val que = PriorityQueue<State>(
        compareBy<State>{
            it.time
        }.thenBy {
            it.point.y
        }.thenBy {
            it.point.x
        })
    que.offer(State(Point(baby.x, baby.y), 0))

    var visited = Array(space.size) {
        BooleanArray(space[it].size) { false }
    }
    visited[baby.y][baby.x] = true

    var time = 0
    while(que.isNotEmpty()) {
        val now = que.poll()
        val size = space[now.point.y][now.point.x]
        if (size != 0 && size < baby.size) {
            time += now.time
            space[now.point.y][now.point.x] = 0
            baby.eat()
            que.clear()
            que.offer(State(now.point, 0))
            visited = Array(space.size) { BooleanArray(space[it].size) { false } }
            visited[now.point.y][now.point.x] = true
            continue
        }

        run {
            listOf(
                {from:Point -> Point(from.x,from.y - 1)},
                {from:Point -> Point(from.x - 1, from.y)},
                {from:Point -> Point(from.x + 1, from.y)},
                {from:Point -> Point(from.x, from.y + 1)},
            ).forEach {
                val moved = it(now.point)

                if (moved.y in space.indices && moved.x in space[moved.y].indices) {// idx 확인
                    if (!visited[moved.y][moved.x]) {// 방문확인
                        val fishSize = space[moved.y][moved.x]
                        if (fishSize <= baby.size) {// 이동
                            que.offer(State(moved, now.time + 1))
                            visited[moved.y][moved.x] = true
                        }
                    }
                }
            }
        }
    }

    println(time)
}
data class Point(val x:Int, val y:Int)

class Shark(var size: Int, var x:Int, var y:Int) {
    private var count = 0

    fun eat() {
        count++

        if (count == size) {
            size++
            count = 0
        }
    }
}