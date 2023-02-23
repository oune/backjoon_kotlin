import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (height, width, range) = readLine().split(" ").map { it.toInt() }

    val originMap = List(height) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    fun subset(): MutableList<List<Int>> {
        val res = mutableListOf<List<Int>>()

        for (masking in 1 until (1 shl width)) {
            val selected = (0 until width).filter { idx ->
                masking and (1 shl idx) != 0
            }
            res.add(selected)
        }
        return res
    }

    val combinations = subset().filter { it.size == 3 }

    data class State(val x:Int, val y:Int, val time:Int)

    var max = 0
    combinations.forEach { list ->
        var count = 0

        val map = List(height) {
            originMap[it].clone()
        }

        for (time in map.lastIndex downTo 0) {
            val enemies = mutableListOf<Pair<Int, Int>>()

            for (start in list) {
                val visited = List(height) { BooleanArray(width) { false } }
                visited[time][start] = true
                val que = LinkedList<State>()
                que.offer(State(start, time, 1))

                while(que.isNotEmpty()) {
                    val now = que.poll()

                    if (map[now.y][now.x] == 1) {
                        enemies.add(Pair(now.x, now.y))
                        break
                    }

                    if (now.time == range)
                        continue

                    val moveList = listOf(
                        {x:Int, y:Int -> Pair(x - 1, y)},
                        {x:Int, y:Int -> Pair(x, y - 1)},
                        {x:Int, y:Int -> Pair(x + 1, y)},
                    )

                    moveList.map { move ->
                        move(now.x, now.y)
                    }.filter { (x, y) ->
                        y in map.indices && x in map[y].indices
                    }.filter { (x, y) ->
                        !visited[y][x]
                    }.forEach { (x, y) ->
                        visited[y][x] = true
                        que.offer(State(x, y, now.time + 1))
                    }
                }
            }

            enemies.distinct().forEach { (x, y) ->
                map[y][x] = 0
                count++
            }
        }
        max = max.coerceAtLeast(count)
    }

    println(max)
}