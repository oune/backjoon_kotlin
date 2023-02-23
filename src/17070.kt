import java.util.LinkedList
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val right = 1
    val down = -1
    val diagonal = 0
    val wall = 1
    val empty = 0

    val size = readLine().toInt()
    val map = List(size) {
        readLine().split(" ").map { it.toInt() }
    }

    data class State(val x:Int, val y:Int, val direction:Int)
    val que = LinkedList<State>()

    que.offer(State(1, 0, right))

    var count = 0
    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.x == map.lastIndex && now.y == map.lastIndex) {
            count++
            continue
        }

        for (direction in listOf(right, down, diagonal)) {
            if (abs(direction - now.direction) > 1)
                continue

            when(direction) {
                right -> {
                    val x = now.x + 1
                    val y = now.y

                    if (y in map.indices && x in map[y].indices)
                        if (map[y][x] == empty)
                            que.offer(State(x, y, direction))
                }
                down -> {
                    val x = now.x
                    val y = now.y + 1

                    if (y in map.indices && x in map[y].indices)
                        if (map[y][x] == empty)
                            que.offer(State(x, y, direction))
                }
                diagonal -> {
                    val x = now.x + 1
                    val y = now.y + 1

                    if (y in map.indices && x in map[y].indices) {
                        if (map[y][x] == wall)
                            continue
                        if (map[now.y][x] == wall)
                            continue
                        if (map[y][now.x] == wall)
                            continue

                        que.offer(State(x, y, direction))
                    }
                }

            }
        }
    }

    println(count)
}