import java.util.PriorityQueue

/*
최소신장트리
 */
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    val parents = List(n) { i ->
        Array(m) { j ->
            Pair(j, i)
        }
    }

    fun findSet(point : Pair<Int,Int>): Pair<Int, Int> {
        val (x, y) = point
        if (point != parents[y][x])
            parents[y][x] = findSet(parents[y][x])

        return parents[y][x]
    }

    fun unionSet(from:Pair<Int, Int>, to:Pair<Int, Int>) {
        val parentFrom = findSet(from)
        val parentTo = findSet(to)

        parents[parentFrom.second][parentFrom.first] = parentTo
    }

    fun isUnion(from:Pair<Int, Int>, to:Pair<Int, Int>): Boolean {
        return findSet(from) == findSet(to)
    }

    val moveList = listOf(
        {x:Int, y:Int -> Pair(x + 1, y)},
        {x:Int, y:Int -> Pair(x - 1, y)},
        {x:Int, y:Int -> Pair(x, y + 1)},
        {x:Int, y:Int -> Pair(x, y - 1)},
    )

    data class State(val from:Pair<Int, Int>, val to :Pair<Int, Int>, val distance:Int)
    val que = PriorityQueue<State>(compareBy { it.distance })

    var last = Pair(-1, -1)
    for (y in map.indices) {
        for (x in map[y].indices) {
            if (map[y][x] == 1) {
                last = Pair(x, y)
                for (move in moveList) {
                    var distance = 0
                    var moved = move(x, y)

                    while (moved.second in map.indices && moved.first in map[moved.second].indices && map[moved.second][moved.first] == 0) {
                        moved = move(moved.first, moved.second)
                        distance++
                    }

                    if (moved.second in map.indices && moved.first in map[moved.second].indices) {
                        if (map[moved.second][moved.first] == 1) {
                            if (distance == 0) {
                                unionSet(Pair(x, y), moved)
                            } else if (distance > 1) {
                                que.add(State(Pair(x, y), moved, distance))
                            }
                        }
                    }
                }
            }
        }
    }


    var sum = 0
    while (que.isNotEmpty()) {
        val (from, to, distance) = que.poll()

        if (!isUnion(from, to)) {
            sum += distance
            unionSet(from, to)
        }
    }

    var isSuccess = true
    for (y in map.indices) {
        for (x in map[y].indices) {
            if (map[y][x] == 0)
                continue

            if (!isUnion(Pair(x, y), last)) {
                isSuccess = false
            }
        }
    }

    println(if (isSuccess) sum else -1)
}