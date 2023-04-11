import java.util.PriorityQueue

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val connected = List(m) {
        readln().split(" ").map { it.toInt() -1 }
    }
    val map = List(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 0 until n) {
        map[0][i] = 0
        map[i][0] = 0
    }

    for ((from, to) in connected) {
        map[from][to] = 0
        map[to][from] = 0
    }

    var total = 0
    val list = mutableListOf<Pair<Int, Int>>()
    data class State(val pre:Int, val pos:Int, val cost:Int)

    val visited = BooleanArray(n) { false }
    visited[0] = true

    val que = PriorityQueue<State>(compareBy { it.cost })
    que.add(State(-1, 1, 0))

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (visited[now.pos])
            continue
        visited[now.pos] = true

        //visit
        if (now.cost > 0) {
            total += now.cost
            list.add(Pair(now.pre + 1, now.pos + 1))
        }

        for (next in map[now.pos].indices) {
            if (next == now.pos)
                continue
            if (visited[next])
                continue

            que.add(State(now.pos, next, map[now.pos][next]))
        }
    }

    println("$total ${list.size}")
    println(list.joinToString("\n"){ "${it.first} ${it.second}"})
}