package test.b2000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (row, _) = readLine().split(" ").map{ it.toInt() }

    val startList = LinkedList<State>()
    val map = Array(row) {
        val arr = readLine().toCharArray()
        arr.forEachIndexed { index, c ->
            if (c == 'L') {
                startList.offer(State(index, it, 0))
            }
        }
        arr
    }

    var max = Int.MIN_VALUE
    startList.forEach { start ->
        val visited = Array(map.size) {
            BooleanArray(map[it].size) { false }
        }

        val que = LinkedList<State>()
        que.offer(start)
        visited[start.y][start.x] = true

        while(que.isNotEmpty()) {
            val now = que.poll()

            if (now.time > max)
                max = now.time

            listOf(
                {state:State ->
                    val (x, y, time) = state
                    State(x + 1, y, time + 1)
                },
                {state:State ->
                    val (x, y, time) = state
                    State(x, y + 1, time + 1)
                },
                {state:State ->
                    val (x, y, time) = state
                    State(x - 1, y, time + 1)
                },
                {state:State ->
                    val (x, y, time) = state
                    State(x, y - 1, time + 1)
                },
            ).forEach {
                val moved = it(now)

                if (map.isInIndices(moved)) {
                    if (map[moved.y][moved.x] == 'L') {
                        if (!visited[moved.y][moved.x]) {
                            que.offer(moved)
                            visited[moved.y][moved.x] = true
                        }
                    }
                }
            }
        }
    }
    print(max)
}

private fun  Array<CharArray>.isInIndices(state: State):Boolean {
    if (state.y in this.indices) {
        if (state.x in this[state.y].indices) {
            return true
        }
    }

    return false
}

private data class State(val x:Int, val y:Int, val time:Int)