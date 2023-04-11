import java.util.PriorityQueue

/*
다익스트라, 그래프 탐색
*/
fun main() {
    val size = readln().toInt()
    val map = List(size) {
        readln().toList()
    }
    val costs = List(size) {
        List(size) {
            IntArray(4) {
                Int.MAX_VALUE
            }
        }
    }

    val doors = mutableListOf<Pair<Int, Int>>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == '#')
                doors.add(Pair(j, i))
        }
    }

    val start = doors.first()
    val end = doors.last()

    val moveList = listOf(
        {point:Pair<Int, Int> -> Pair(point.first + 1, point.second)},
        {point:Pair<Int, Int> -> Pair(point.first, point.second + 1)},
        {point:Pair<Int, Int> -> Pair(point.first - 1, point.second)},
        {point:Pair<Int, Int> -> Pair(point.first, point.second - 1)},
    )
    data class State(val x:Int, val y:Int, val direction: Int, val cost:Int)
    val que = PriorityQueue<State>(compareBy { it.cost })

    for (direction in moveList.indices) {
        que.add(State(start.first, start.second, direction, 0))
        costs[start.second][start.first][direction] = 0
    }

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (costs[now.y][now.x][now.direction] < now.cost)
            continue

        when(map[now.y][now.x]) {
            '#' -> {
                val move = moveList[now.direction]
                val moved = move(Pair(now.x, now.y))

                if (moved.second !in map.indices || moved.first !in map[moved.second].indices)
                    continue

                if (costs[moved.second][moved.first][now.direction] > now.cost) {
                    que.add(State(moved.first, moved.second, now.direction, now.cost))
                    costs[moved.second][moved.first][now.direction] = now.cost
                }
            }
            '.' -> {
                val move = moveList[now.direction]
                val moved = move(Pair(now.x, now.y))

                if (moved.second !in map.indices || moved.first !in map[moved.second].indices)
                    continue

                if (costs[moved.second][moved.first][now.direction] > now.cost) {
                    que.add(State(moved.first, moved.second, now.direction, now.cost))
                    costs[moved.second][moved.first][now.direction] = now.cost
                }
            }
            '!' -> {
                val directions = listOf(-1, 1).map {
                    (moveList.size + it + now.direction) % moveList.size
                }

                for (direction in directions) {
                    val move = moveList[direction]
                    val moved = move(Pair(now.x, now.y))

                    if (moved.second !in map.indices || moved.first !in map[moved.second].indices)
                        continue

                    val newCost = now.cost + 1

                    if (costs[moved.second][moved.first ][direction] > newCost) {
                        que.add(State(moved.first, moved.second, direction, newCost))
                        costs[moved.second][moved.first][direction] = newCost
                    }
                }

                val move = moveList[now.direction]
                val moved = move(Pair(now.x, now.y))

                if (moved.second !in map.indices || moved.first !in map[moved.second].indices)
                    continue

                if (costs[moved.second][moved.first][now.direction] > now.cost) {
                    que.add(State(moved.first, moved.second, now.direction, now.cost))
                    costs[moved.second][moved.first][now.direction] = now.cost
                }
            }
        }
    }

    println(costs[end.second][end.first].min())
//    println(costs.joinToString("\n"){ it.joinToString(" "){ it.min().toString() } })
}