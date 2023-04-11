import java.util.PriorityQueue

/*
최소신장 트리 프림 알고리즘
 */
fun main() {
    val size = readln().toInt()
    val map = List(size) {
        readln().split(" ").filter { it != "" }.map { it.toInt() }
    }
    data class State(val from:Int, val to:Int, val cost:Int)

    val que = PriorityQueue<State>(compareBy { it.cost })
    for (i in 1 .. map[0].lastIndex)
        que.add(State(0, i, map[0][i]))

    val visited = BooleanArray(size) { false }
    visited[0] = true

    var total = 0
    val list = mutableListOf<Pair<Int, Int>>()

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.cost < 0)
            total += -now.cost

        if (visited[now.to])
            continue
        visited[now.to] = true

        if (now.cost > 0) {
            list.add(Pair(now.from + 1, now.to + 1))
            total += now.cost
        }

        for (next in map[now.to].indices) {
            if (visited[next])
                continue
            if (next == now.to)
                continue

            que.add(State(now.to, next, map[now.to][next]))
        }
    }

    println("$total ${list.size}")
    println(list.joinToString("\n"){ "${it.first} ${it.second}" })
}