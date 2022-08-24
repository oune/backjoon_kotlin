package test.b6000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (destX, destY, n) = readLine().split(" ").map { it.toInt() }
    val mapSize = 1001
    val isMovable = Array(mapSize) {
        BooleanArray(mapSize) { true }
    }

    repeat(n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        isMovable[y + 500][x + 500] = false
    }

    data class Point(val x:Int, val y:Int)

    val goal = Point(destX + 500, destY + 500)
    val start = Point(500, 500)

    fun bfs(start:Point, goal:Point) :Int {
        data class State(val point:Point, val distance:Int)
        val que = LinkedList<State>()
        val visited = Array(isMovable.size) {
            BooleanArray(isMovable[it].size) {
                false
            }
        }

        que.offer(State(start, 0))
        visited[start.y][start.x] = true

        while (que.isNotEmpty()) {
            val now = que.poll()

            if (now.point == goal) {
                return now.distance
            }

            listOf(
                {point:Point -> Point(point.x + 1, point.y)},
                {point:Point -> Point(point.x - 1, point.y)},
                {point:Point -> Point(point.x, point.y + 1)},
                {point:Point -> Point(point.x, point.y - 1)},
            ).forEach {
                val moved = it(now.point)

                if (moved.y in visited.indices && moved.x in visited[moved.y].indices)
                    if (isMovable[moved.y][moved.x])
                        if (!visited[moved.y][moved.x]) {
                            que.offer(State(moved, now.distance + 1))
                            visited[moved.y][moved.x] = true
                        }

            }
        }
        return -1
    }

    println(bfs(start, goal))
}